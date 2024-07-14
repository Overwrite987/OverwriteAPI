package ru.overwrite.api.logging;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.overwrite.api.Logger;

public class BukkitLogger implements Logger {

    public void info(@NotNull JavaPlugin plugin, @NotNull String msg) {
        plugin.getLogger().info(msg);
    }

    public void warn(@NotNull JavaPlugin plugin, @NotNull String msg) {
        plugin.getLogger().warning(msg);
    }

}
