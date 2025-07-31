package ru.overwrite.api.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class RegionUtils {

    public ApplicableRegionSet getApplicableRegions(@NotNull Location location) {
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer()
                .get(BukkitAdapter.adapt(location.getWorld()));
        if (regionManager == null || regionManager.getRegions().isEmpty())
            return null;
        return regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(location));
    }

    public void createRegion(@NotNull Location centerLocation, int radius, @NotNull String regionName) {

        World world = centerLocation.getWorld();
        BukkitWorld bukkitWorld = new BukkitWorld(world);

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = container.get(bukkitWorld);

        ProtectedCuboidRegion protectedRegion = createProtectedCuboidRegion(centerLocation, radius, regionName);

        regionManager.addRegion(protectedRegion);
    }

    public @NotNull ProtectedCuboidRegion createProtectedCuboidRegion(@NotNull Location centerLocation, int radius, @NotNull String regionName) {
        BlockVector3 minPoint = BlockVector3.at(
                centerLocation.getX() - radius,
                centerLocation.getY() - radius,
                centerLocation.getZ() - radius
        );

        BlockVector3 maxPoint = BlockVector3.at(
                centerLocation.getX() + radius,
                centerLocation.getY() + radius,
                centerLocation.getZ() + radius
        );

        return new ProtectedCuboidRegion(regionName, minPoint, maxPoint);
    }

    public void deleteRegion(@NotNull String regionName, @NotNull String worldName) {
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(Bukkit.getWorld(worldName)));

        if (regionManager != null && regionManager.hasRegion(regionName)) {
            regionManager.removeRegion(regionName);
        } else {
            Bukkit.getConsoleSender().sendMessage("Регион с именем " + regionName + " не найден в мире " + worldName);
        }
    }

    public BlockVector3 getRegionCenter(ProtectedRegion region) {
        if (region == null) {
            return null;
        }

        BlockVector3 minPoint = region.getMinimumPoint();
        BlockVector3 maxPoint = region.getMaximumPoint();

        double centerX = (minPoint.getX() + maxPoint.getX()) / 2.0;
        double centerY = (minPoint.getY() + maxPoint.getY()) / 2.0;
        double centerZ = (minPoint.getZ() + maxPoint.getZ()) / 2.0;

        return BlockVector3.at(centerX, centerY, centerZ);
    }

    public BlockVector3 getMinPoint(ProtectedRegion region) {
        if (region != null) {
            return region.getMinimumPoint();
        } else {
            return null;
        }
    }

    public BlockVector3 getMaxPoint(ProtectedRegion region) {
        if (region != null) {
            return region.getMaximumPoint();
        } else {
            return null;
        }
    }
}
