package ru.overwrite.api;

import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.overwrite.api.actions.ActionRegistry;
import ru.overwrite.api.commons.VersionUtils;
import ru.overwrite.api.logging.BukkitLogger;
import ru.overwrite.api.logging.PaperLogger;

public final class OvApi extends JavaPlugin {

    @Getter
    private final Logger pluginLogger = VersionUtils.FOLIA ? new PaperLogger() : new BukkitLogger();

    @Getter
    private final ActionRegistry actionRegistry = new ActionRegistry(this);

    @Getter
    private static OvApi instance;

    @Override
    public void onEnable() {
        instance = this;
        PluginManager pluginManager = getServer().getPluginManager();
        if (pluginManager.isPluginEnabled("WorldGuard")) {
            pluginLogger.info(this, "§3WorldGuard был подключён");
        }
        pluginLogger.info(this, "§aУспешно запущено!");
    }

    @Override
    public void onDisable() {
        pluginLogger.info(this, "§aУспешно отключено!");
    }

}
