package org.forkjoin.apikit.spring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class DateTimeUtils {

    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATEFORMAT = "yyyy-MM-dd";

    public static String format(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    public static Date parse(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        dateFormat.setLenient(false);
        return dateFormat.parse(date);
    }
}
