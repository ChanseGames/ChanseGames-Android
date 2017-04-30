package com.chanse.games.sdk.common;

import java.util.Locale;

/**
 * All time related calculations will be done here.
 *
 * Created by incnayak on 4/30/2017.
 */

public class TimeUtil {

    public static String formatElapsedTime(long millis) {
        String retVal;
        long secs = millis / 1000;
        int hours = (int) (secs / 3600);
        int mins = (int) ((secs % 3600) / 60);
        int finalSecs = (int) (secs % 60);
        if (hours != 0) {
            retVal = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, mins, finalSecs);
        } else {
            retVal = String.format(Locale.getDefault(), "%02d:%02d", mins, finalSecs);
        }
        return retVal;
    }
}
