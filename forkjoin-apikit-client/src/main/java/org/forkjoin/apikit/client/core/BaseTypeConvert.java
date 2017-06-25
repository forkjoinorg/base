package org.forkjoin.apikit.client.core;

import org.apache.commons.codec.binary.Base64;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zuoge85@gmail.com on 2017/6/15.
 */
public class BaseTypeConvert implements TypeConvert {

    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private  String format = FORMAT;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public static String format(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    @Override
    public String convert(Object source) {
        if (source == null) {
            return null;
        }
        if (source instanceof Date) {
            return format((Date) source);
        } else if (source instanceof byte[]) {
            return Base64.encodeBase64URLSafeString((byte[]) source);
        } else if (source instanceof List) {
            List list = (List) source;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(convert(list.get(i)));
            }
            return sb.toString();
        } else {
            return source.toString();
        }
    }
}
