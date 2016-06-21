package org.forkjoin.util;

import org.apache.commons.lang3.CharUtils;

public final class StringExpandUtils extends org.apache.commons.lang3.StringUtils {
    public static String unicodeEncode(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(CharUtils.unicodeEscaped(str.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * 注意字处理&lt;&gt;两个符号,其他不处理
     *
     * @param str
     * @return
     */
    public static String escapeHtml(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('<' == c) {
                sb.append("&lt;");
            } else if ('>' == c) {
                sb.append("&gt;");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static boolean checkLengthRange(String str, int min, int max) {
        int l = (str == null ? 0 : str.length());
        return l >= min && l <= max;
    }

    /**
     *
     * @param value
     * @return 返回字符串长度，中文字符长2.
     */
    public static int getChEnLength(String value) {
        int len = 0;
        for (int i = 0; i < value.length(); i++) {
            int ch = value.charAt(i);
            if (ch >= 0x4e00 && ch <= 0x9fa5) {
                len += 2;
            } else {
                len++;
            }
        }
        return len;
    }
}
