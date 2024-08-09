package ru.overwrite.api.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Expiry;
import ru.overwrite.api.cache.CaffeineFactory;

import java.util.concurrent.TimeUnit;

public class TimedExpiringSet<E> {

    private final Cache<E, Long> cache;
    private final TimeUnit unit;

    public TimedExpiringSet(TimeUnit unit) {
        this.unit = unit;
        this.cache = CaffeineFactory.newBuilder()
                .expireAfter(new Expiry<E, Long>() {
                    @Override
                    public long expireAfterCreate(E key, Long expiryTime, long currentTime) {
                        return expiryTime;
                    }

                    @Override
                    public long expireAfterUpdate(E key, Long expiryTime, long currentTime, long currentDuration) {
                        return expiryTime;
                    }

                    @Override
                    public long expireAfterRead(E key, Long expiryTime, long currentTime, long currentDuration) {
                        return currentDuration;
                    }
                })
                .build();
    }

    public boolean add(E item, long duration) {
        boolean present = contains(item);
        long expiryDuration = unit.toNanos(duration);
        this.cache.put(item, expiryDuration);
        return !present;
    }

    public boolean contains(E item) {
        return this.cache.getIfPresent(item) != null;
    }

    public void remove(E item) {
        this.cache.invalidate(item);
    }

    public long size() {
        return this.cache.estimatedSize();
    }

    public void clear() {
        this.cache.invalidateAll();
    }
}
