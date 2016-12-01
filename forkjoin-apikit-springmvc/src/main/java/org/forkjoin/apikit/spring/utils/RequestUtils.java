package org.forkjoin.apikit.spring.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author zuoge85 on 15/5/30.
 */
public class RequestUtils {
    /**
     * 吧请求dump成json
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static String dump(HttpServletRequest request) throws Exception {
//        StringBuilder sb = new StringBuilder();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        log.error("错误{}:{}",
//                request.getRemoteAddr(), request.getRemoteHost(), requestURI,
//
//                );
//        sb.append(request.getRequestURL());
//
//        sb.append("parameters:");
//
//        sb.append("headers:{");
//
//        i = 0;
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            String value = request.getHeader(name);
//
//            if (i > 0) {
//                sb.append(",");
//            }
//            sb.append(name);
//            sb.append(":");
//            sb.append(value);
//            sb.append(",");
//            i++;
//        }
//        sb.append("}");
        HashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("url", request.getRequestURL());
        hashMap.put("method", request.getMethod());
        hashMap.put("remoteAddr", request.getRemoteAddr());
        hashMap.put("remoteHost", request.getRemoteHost());


        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Object[] cookieArray = new Object[cookies.length];
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                HashMap<String, Object> cookieMap = new HashMap<>();
                cookieMap.put("name", cookie.getName());
                cookieMap.put("value", cookie.getValue());
                cookieMap.put("maxAge", cookie.getMaxAge());
                cookieMap.put("path", cookie.getPath());
            }
            hashMap.put("cookies", cookieArray);
        }
        Map<String, String> parameters = new HashMap<>();

        for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
            String valueStr = Arrays.toString(e.getValue());
            parameters.put(e.getKey(), valueStr.substring(0, Math.min(64, valueStr.length())));
        }

        hashMap.put("parameters", parameters);
        HashMap<String, Object> headerMap = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            headerMap.put(name, value);
        }
        hashMap.put("headers", headerMap);
        return JsonUtils.serialize(hashMap);
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        int i = ip.indexOf(",");
        if(i > -1){
            return ip.split(",")[0];
        }
        return ip;
    }
}
