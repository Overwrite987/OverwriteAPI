package ru.overwrite.api.logging;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.overwrite.api.Logger;

public class PaperLogger implements Logger {

    private final LegacyComponentSerializer legacySection = LegacyComponentSerializer.legacySection();

    public void info(@NotNull JavaPlugin plugin, @NotNull String msg) {
        plugin.getComponentLogger().info(legacySection.deserialize(msg));
    }

    public void warn(@NotNull JavaPlugin plugin, @NotNull String msg) {
        plugin.getComponentLogger().warn(legacySection.deserialize(msg));
    }

}
