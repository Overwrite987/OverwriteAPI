package ru.overwrite.api.actions;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.overwrite.api.OvApi;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionRegistry {

    private static final Pattern ACTION_PATTERN = Pattern.compile("\\[(\\S+)] ?(.*)");

    private final OvApi plugin;
    private final Map<String, ActionType> types;

    public ActionRegistry(OvApi plugin) {
        this.plugin = plugin;
        this.types = new HashMap<>();
    }

    public void register(@NotNull ActionType type) {
        if (types.put(type.key().toString(), type) != null) {
            plugin.getSLF4JLogger().warn("Type '{}' was overridden with '{}'", type.key(), type.getClass().getName());
        }
        types.putIfAbsent(type.key().value(), type);
    }

    public @Nullable ActionType getType(@NotNull String typeStr) {
        return types.get(typeStr.toLowerCase(Locale.ROOT));
    }

    public @Nullable Action resolveAction(@NotNull String actionStr) {
        Matcher matcher = ACTION_PATTERN.matcher(actionStr);
        if (!matcher.matches()) return null;
        ActionType type = getType(matcher.group(1));
        if (type == null) return null;
        return type.instance(matcher.group(2), plugin);
    }

    private List<Action> getActionList(String pluginName, List<String> actionStrings) {
        List<Action> actions = new ArrayList<>(actionStrings.size());
        for (String actionStr : actionStrings) {
            actionStr = pluginName+":"+actionStr;
            try {
                actions.add(Objects.requireNonNull(resolveAction(actionStr), "Type doesn't exist"));
            } catch (Exception ex) {
                plugin.getSLF4JLogger().warn("Couldn't create action for string '{}'", actionStr, ex);
            }
        }
        return actions;
    }
}

