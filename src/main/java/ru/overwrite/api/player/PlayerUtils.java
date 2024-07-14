package ru.overwrite.api.player;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PlayerUtils {

    public static void sendTitleMessage(@NotNull String[] titleMessages, @NotNull Player p) {
        if (titleMessages.length > 5) {
            Bukkit.getConsoleSender().sendMessage("Unable to send title. " + Arrays.toString(titleMessages));
            return;
        }
        String title = titleMessages[0];
        String subtitle = titleMessages.length >= 2 ? titleMessages[1] : "";
        int fadeIn = titleMessages.length >= 3 ? Integer.parseInt(titleMessages[2]) : 10;
        int stay = titleMessages.length >= 4 ? Integer.parseInt(titleMessages[3]) : 70;
        int fadeOut = titleMessages.length == 5 ? Integer.parseInt(titleMessages[4]) : 20;
        p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    public static void sendSound(@NotNull String[] soundArgs, @NotNull Player p) {
        if (soundArgs.length > 3) {
            Bukkit.getConsoleSender().sendMessage("Unable to send sound. " + Arrays.toString(soundArgs));
            return;
        }
        Sound sound = Sound.valueOf(soundArgs[0]);
        float volume = soundArgs.length >= 2 ? Float.parseFloat(soundArgs[1]) : 1.0f;
        float pitch = soundArgs.length == 3 ? Float.parseFloat(soundArgs[2]) : 1.0f;
        p.playSound(p.getLocation(), sound, volume, pitch);
    }

    public static void giveEffect(@NotNull String[] effectArgs, @NotNull Player p) {
        if (effectArgs.length > 3) {
            Bukkit.getConsoleSender().sendMessage("Unable to give effect. " + Arrays.toString(effectArgs));
            return;
        }
        PotionEffectType effectType = PotionEffectType.getByName(effectArgs[0]);
        int duration = effectArgs.length >= 2 ? Integer.parseInt(effectArgs[1]) : 1;
        int amplifier = effectArgs.length == 3 ? Integer.parseInt(effectArgs[2]) : 1;
        PotionEffect effect = new PotionEffect(effectType, duration, amplifier);
        p.addPotionEffect(effect);
    }

    public static String getIp(@NotNull Player player) {
        return player.getAddress().getAddress().getHostAddress();
    }
}
