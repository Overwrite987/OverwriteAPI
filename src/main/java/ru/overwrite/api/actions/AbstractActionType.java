package ru.overwrite.api.actions;

import net.kyori.adventure.key.Key;
import org.bukkit.plugin.java.JavaPlugin;

public class AbstractActionType {

    protected final Key KEY;

    public AbstractActionType(JavaPlugin plugin, String type) {
        KEY = Key.key(plugin.getName() + ":" + type);
    }
}
