package com.n1.city.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHelper {

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isContainingSymbols(CharSequence target) {
        String symbols = "[`~!#$^&*()=|{}':;',\\[\\].<>/?~！#￥……&*（）——|{}【】‘'；：”“'。，、？]";
        Pattern p = Pattern.compile(symbols);
        Matcher m = p.matcher(target.toString());
        return m.find();
    }

    public static String nowToString() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
    }

    public static String roundToString(int round) {
        return new String[] {"1st", "2nd", "3rd", "4rd", "OT1", "OT2", "OT3", "OT4", "OT5", "OT6", "OT7", "OT8", "OT8" } [round];
    }

    public static String toGameTimeString(long remainingTime) {
        long seconds = remainingTime % 60;
        long minutes = (remainingTime - seconds) / 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
