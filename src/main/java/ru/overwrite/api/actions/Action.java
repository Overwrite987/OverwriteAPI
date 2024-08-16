package ru.overwrite.api.actions;

import org.jetbrains.annotations.NotNull;

public interface Action {

    void perform(@NotNull Object... params);

}

