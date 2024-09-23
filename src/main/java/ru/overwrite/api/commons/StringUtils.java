package ru.overwrite.api.commons;

import it.unimi.dsi.fastutil.chars.CharOpenHashSet;
import it.unimi.dsi.fastutil.chars.CharSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})");
    private static final char COLOR_CHAR = '§';
    
    public static String colorize(String message) {
        if (message == null || message.isEmpty()) {
            return message;
        }
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
        return translateAlternateColorCodes('&', message);
    }

    private static final CharSet CODES = new CharOpenHashSet(new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f',
            'A', 'B', 'C', 'D', 'E', 'F',
            'k', 'l', 'm', 'n', 'o', 'r', 'x',
            'K', 'L', 'M', 'N', 'O', 'R', 'X'
    });

    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();

        for (int i = 0, length = b.length - 1; i < length; ++i) {
            if (b[i] == altColorChar && CODES.contains(b[i + 1])) {
                b[i++] = '§';
                b[i] = Character.toLowerCase(b[i]);
            }
        }

        return new String(b);
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
