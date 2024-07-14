package ru.overwrite.api.commons;

public class TimeUtils {

    public static String getTime(int time, String hoursMark, String minutesMark, String secondsMark) {
        int hours = getHours(time);
        int minutes = getMinutes(time);
        int seconds = getSeconds(time);

        StringBuilder result = new StringBuilder();

        if (hours > 0) {
            result.append(hours).append(hoursMark);
        }

        if (minutes > 0 || (hours > 0 && seconds == 0)) {
            result.append(minutes).append(minutesMark);
        }

        if (seconds > 0 || (minutes == 0 && hours == 0)) {
            result.append(seconds).append(secondsMark);
        }

        return result.toString();
    }

    public static int getHours(int time) {
        return time / 3600;
    }

    public static int getMinutes(int time) {
        return (time % 3600) / 60;
    }

    public static int getSeconds(int time) {
        return time % 60;
    }

}
