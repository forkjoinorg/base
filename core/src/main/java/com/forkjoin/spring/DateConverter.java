package com.forkjoin.spring;


import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import com.forkjoin.util.DateTimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if(StringUtils.isBlank(source)){
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.FORMAT);
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            SimpleDateFormat dateFormat1 = new SimpleDateFormat(DateTimeUtils.DATEFORMAT);
            dateFormat.setLenient(false);
            try {
                return dateFormat1.parse(source);
            }catch (ParseException e1){
                throw new RuntimeException(e.getMessage(),e1);
            }


        }
    }
}