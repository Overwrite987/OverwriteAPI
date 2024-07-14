package ru.overwrite.api;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface Logger {

    void info(@NotNull JavaPlugin plugin, @NotNull String msg);
    void warn(@NotNull JavaPlugin plugin, @NotNull String msg);

}
