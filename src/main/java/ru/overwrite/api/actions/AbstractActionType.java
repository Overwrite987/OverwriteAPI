package ru.overwrite.api.actions;

import net.kyori.adventure.key.Key;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractActionType implements ActionType {

    protected final Key KEY;

    public AbstractActionType(JavaPlugin plugin, String type) {
        KEY = Key.key(plugin.getName() + ":" + type);
    }

    @Override
    public @NotNull Action instance(@NotNull String context, @NotNull JavaPlugin plugin) {
        return new AbstractAction(context);
    }

    @Override
    public @NotNull Key key() {
        return KEY;
    }

    private record AbstractAction(@NotNull String command) implements Action {
        @Override
        public void perform(@NotNull Object... params) {
            // do nothing
        }
    }
}