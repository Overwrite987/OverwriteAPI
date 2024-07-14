package ru.overwrite.api.commons;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class WorldUtils {

    public static Location parseLocation(@NotNull String[] location) {
        if (location.length > 6) {
            Bukkit.getConsoleSender().sendMessage("Unable to parse location. " + Arrays.toString(location));
            return null;
        }
        World world = Bukkit.getWorld(location[0]);
        double x = location.length >= 2 ? Double.parseDouble(location[1]) : 0;
        double y = location.length >= 3 ? Double.parseDouble(location[2]) : 0;
        double z = location.length >= 4 ? Double.parseDouble(location[3]) : 0;
        float yaw = location.length >= 5 ? Float.parseFloat(location[4]) : 0;
        float pitch = location.length == 6 ? Float.parseFloat(location[5]) : 0;
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static String locationToArrayString(@NotNull Location location) {
        return location.getWorld().getName() + ";"
                + location.getBlockX() + ";"
                + location.getBlockY() + ";"
                + location.getBlockZ() + ";"
                + location.getYaw() + ";"
                + location.getPitch();
    }
}
