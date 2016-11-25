package org.forkjoin.apikit.utils;

import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.info.JavadocInfo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoge85@gmail.com on 2016/11/23.
 */
public class CommentUtils {


    public static String formatBaseComment(JavadocInfo comment, String start) {
        if (comment == null) {
            return start;
        }
        StringBuilder sb = new StringBuilder();

        for (String tagName : comment.getTags().keySet()) {
            sb.append(start);
            formatCommentItem(comment, start, sb, tagName);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        } else {
            return start;
        }
        return sb.toString();
    }
    public static String formatComment(JavadocInfo comment, String start) {

        if (comment == null) {
            return start;
        }
        StringBuilder sb = new StringBuilder();
        for (String tagName : comment.getTags().keySet()) {
            sb.append(start);
            if (StringUtils.isNotEmpty(tagName)) {
                sb.append(tagName);
                sb.append(' ');
            }
            formatCommentItem(comment, start, sb, tagName);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private static void formatCommentItem(JavadocInfo comment, String start, StringBuilder sb, String tagName) {
        Collection<String> fragments = comment.getTags().get(tagName);
        int i = 0;
        for (String fragment : fragments) {
            if (i > 0) {
                sb.append(start);
            }
            sb.append(fragment);
            sb.append('\n');
            i++;
        }
    }

    public static Map<String, String> commentToMap(JavadocInfo comment) {
        if (comment == null) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<>();

        for (String tagName : comment.getTags().keySet()) {
            StringBuilder sb = new StringBuilder();
            String paramName = null;

            boolean isParam = "@param".equals(tagName);

            Collection<String> fragments = comment.getTags().get(tagName);
            for (String fragment : fragments) {
                paramName = fragment;
                if (!isParam) {
                    sb.append(fragment);
                    sb.append(' ');
                }
            }

            if (isParam && paramName != null) {
                map.put(paramName, sb.toString());
            } else {
                map.put(tagName, sb.toString());
            }
        }
        return map;
    }
}
