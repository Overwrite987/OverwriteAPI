package ru.overwrite.api;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.overwrite.api.commons.VersionUtils;
import ru.overwrite.api.logging.BukkitLogger;
import ru.overwrite.api.logging.PaperLogger;

public final class OvApi extends JavaPlugin {

    public static final Logger pluginLogger = VersionUtils.FOLIA ? new PaperLogger() : new BukkitLogger();

    @Override
    public void onEnable() {
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
