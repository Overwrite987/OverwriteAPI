package ru.overwrite.api.actions;

import net.kyori.adventure.key.Keyed;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface ActionType extends Keyed {

    @NotNull Action instance(@NotNull String context, @NotNull JavaPlugin plugin);

}

