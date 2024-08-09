package ru.overwrite.api.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Expiry;

import java.util.concurrent.TimeUnit;

public class TimedExpiringMap<K, V> {

    private final Cache<K, V> cache;
    private final TimeUnit unit;

    public TimedExpiringMap(TimeUnit unit) {
        this.unit = unit;
        this.cache = CaffeineFactory.newBuilder()
                .expireAfter(new Expiry<K, V>() {
                    @Override
                    public long expireAfterCreate(K key, V value, long currentTime) {
                        return currentTime;
                    }

                    @Override
                    public long expireAfterUpdate(K key, V value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }

                    @Override
                    public long expireAfterRead(K key, V value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }
                })
                .build();
    }

    public void put(K key, V value, long duration) {
        long expiryDuration = unit.toNanos(duration);
        this.cache.put(key, value);
    }

    public V get(K key) {
        return this.cache.getIfPresent(key);
    }

    public boolean containsKey(K key) {
        return this.cache.getIfPresent(key) != null;
    }

    public void remove(K key) {
        this.cache.invalidate(key);
    }

    public long size() {
        return this.cache.estimatedSize();
    }

    public void clear() {
        this.cache.invalidateAll();
    }
}
