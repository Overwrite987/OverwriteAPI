package ru.overwrite.api.commons;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@UtilityClass
public class EffectUtils {

    public PotionEffect parseEffect(@NotNull String[] effect) {
        if (effect.length > 3) {
            Bukkit.getConsoleSender().sendMessage("Unable to parse location. " + Arrays.toString(effect));
            return null;
        }
        PotionEffectType effectType = PotionEffectType.getByName(effect[0]);
        int duration = effect.length >= 2 ? Integer.parseInt(effect[1]) : 10;
        int level = effect.length == 3 ? Integer.parseInt(effect[2]) - 1 : 0;
        return new PotionEffect(effectType, duration, level);
    }
}
