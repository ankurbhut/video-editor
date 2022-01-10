package com.video_editor.videoTrimmer.utils;

public class ConvertDurationUtils {

    public static String convertDurationText(int duration) {
        if (duration > 0) {
            int minute = duration / 60;
            int second = duration % 60;
            return (minute >= 10 ? minute : "0" + minute) + ":" + (second >= 10 ? second : "0" + second);
        }
        return "00:00";
    }

    public static String convertDurationFormat(int totalSeconds) {
        int seconds = (totalSeconds % 60);
        int minutes = (totalSeconds % 3600) / 60;
        int hours = (totalSeconds % 86400) / 3600;
        int days = (totalSeconds % (86400 * 30)) / 86400;
        String h = ((hours < 10) ? "0" : "") + hours;
        String m = ((minutes < 10) ? "0" : "") + minutes;
        String s = ((seconds < 10) ? "0" : "") + seconds;
        return h + ":" + m + ":" + s;
    }
}
