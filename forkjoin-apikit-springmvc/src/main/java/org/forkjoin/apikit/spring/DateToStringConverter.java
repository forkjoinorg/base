package org.forkjoin.apikit.spring;


import org.forkjoin.apikit.spring.utils.DateTimeUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 */
public class DateToStringConverter implements Converter<Date, String> {
    private String format;

    public DateToStringConverter() {
        this(DateTimeUtils.FORMAT);
    }

    public DateToStringConverter(String format) {
        this.format = format;
    }

    @Override
    public String convert(Date source) {
        if (source == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        return dateFormat.format(source);
    }
}