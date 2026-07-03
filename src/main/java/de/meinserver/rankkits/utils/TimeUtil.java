package de.meinserver.rankkits.utils;

public class TimeUtil {

    public static String format(long seconds) {

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        if (hours > 0) {
            return hours + "h " + minutes + "m";
        }

        if (minutes > 0) {
            return minutes + "m " + secs + "s";
        }

        return secs + "s";
    }
}
