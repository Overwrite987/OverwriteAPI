package ru.overwrite.api.commons;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.md_5.bungee.api.ChatColor.COLOR_CHAR;

public class StringUtils {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})");

    public static String colorize(@Nullable String message) {
        if (message == null || message.isEmpty() || message.isBlank()) {
            return message;
        }
        if (VersionUtils.SUB_VERSION >= 16) {
            Matcher matcher = HEX_PATTERN.matcher(message);
            StringBuilder builder = new StringBuilder(message.length() + 32);
            while (matcher.find()) {
                String group = matcher.group(1);
                matcher.appendReplacement(builder,
                        COLOR_CHAR + "x" +
                                COLOR_CHAR + group.charAt(0) +
                                COLOR_CHAR + group.charAt(1) +
                                COLOR_CHAR + group.charAt(2) +
                                COLOR_CHAR + group.charAt(3) +
                                COLOR_CHAR + group.charAt(4) +
                                COLOR_CHAR + group.charAt(5));
            }
            message = matcher.appendTail(builder).toString();
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    // Original - org.apache.commons.lang3.StringUtils#isNumeric
    public static boolean isNumeric(@Nullable CharSequence cs) {
        if (cs == null || cs.isEmpty()) {
            return false;
        } else {
            int sz = cs.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String replaceEach(@Nullable String text, @NotNull String[] searchList, @NotNull String[] replacementList) {
        if (text == null || text.isEmpty() || searchList.length == 0 || replacementList.length == 0) {
            return text;
        }

        if (searchList.length != replacementList.length) {
            throw new IllegalArgumentException("Search and replacement arrays must have the same length.");
        }

        StringBuilder result = new StringBuilder(text);

        for (int i = 0; i < searchList.length; i++) {
            String search = searchList[i];
            String replacement = replacementList[i];

            int start = 0;

            while ((start = result.indexOf(search, start)) != -1) {
                result.replace(start, start + search.length(), replacement);
                start += replacement.length();
            }
        }

        return result.toString();
    }
}
