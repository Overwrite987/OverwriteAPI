package ru.overwrite.api.commons;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class StringUtils {

    private final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})");
    private final char COLOR_CHAR = '§';

    public String colorize(@Nullable String message) {
        if (message == null || message.isEmpty()) {
            return message;
        }
        final Matcher matcher = HEX_PATTERN.matcher(message);
        final StringBuilder builder = new StringBuilder(message.length() + 32);
        while (matcher.find()) {
            final String group = matcher.group(1);
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
        return translateAlternateColorCodes('&', message);
    }

    public String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        final char[] b = textToTranslate.toCharArray();

        for (int i = 0, length = b.length - 1; i < length; ++i) {
            if (b[i] == altColorChar && isValidColorCharacter(b[i + 1])) {
                b[i++] = COLOR_CHAR;
                b[i] |= 0x20;
            }
        }

        return new String(b);
    }

    private boolean isValidColorCharacter(char c) {
        return switch (c) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D',
                 'E', 'F', 'r', 'R', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'x', 'X' -> true;
            default -> false;
        };
    }

    public String getTime(long time, String hoursMark, String minutesMark, String secondsMark) {
        final long hours = getHours(time);
        final long minutes = getMinutes(time);
        final long seconds = getSeconds(time);

        final StringBuilder result = new StringBuilder();

        if (hours > 0) {
            result.append(hours).append(hoursMark);
        }

        if (minutes > 0 || hours > 0) {
            result.append(minutes).append(minutesMark);
        }

        result.append(seconds).append(secondsMark);

        return result.toString();
    }

    public long getHours(long time) {
        return time / 3600;
    }

    public long getMinutes(long time) {
        return (time % 3600) / 60;
    }

    public long getSeconds(long time) {
        return time % 60;
    }

    public String getTime(int time, String hoursMark, String minutesMark, String secondsMark) {
        final int hours = getHours(time);
        final int minutes = getMinutes(time);
        final int seconds = getSeconds(time);

        final StringBuilder result = new StringBuilder();

        if (hours > 0) {
            result.append(hours).append(hoursMark);
        }

        if (minutes > 0 || hours > 0) {
            result.append(minutes).append(minutesMark);
        }

        result.append(seconds).append(secondsMark);

        return result.toString();
    }

    public int getHours(int time) {
        return time / 3600;
    }

    public int getMinutes(int time) {
        return (time % 3600) / 60;
    }

    public int getSeconds(int time) {
        return time % 60;
    }

    public boolean isNumeric(@Nullable CharSequence cs) {
        if (cs == null || cs.isEmpty()) {
            return false;
        }
        int sz = cs.length();

        for (int i = 0; i < sz; ++i) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String replaceEach(@Nullable String text, @NotNull String[] searchList, @NotNull String[] replacementList) {
        if (text == null || text.isEmpty() || searchList.length == 0 || replacementList.length == 0) {
            return text;
        }

        if (searchList.length != replacementList.length) {
            throw new IllegalArgumentException("Search and replacement arrays must have the same length.");
        }

        final StringBuilder result = new StringBuilder(text);

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
