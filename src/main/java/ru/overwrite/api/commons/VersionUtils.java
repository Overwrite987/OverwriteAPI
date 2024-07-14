package ru.overwrite.api.commons;

import org.bukkit.Bukkit;

public class VersionUtils {

    public static final int SUB_VERSION = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);

    public static final int VOID_LEVEL = SUB_VERSION >= 18 ? -60 : 0;

    public static final boolean FOLIA;

    static {
        boolean folia;
        try {
            Class.forName("io.papermc.paper.threadedregions.scheduler.AsyncScheduler");
            folia = true;
        } catch (ClassNotFoundException e) {
            folia = false;
        }
        FOLIA = folia;
    }

}
