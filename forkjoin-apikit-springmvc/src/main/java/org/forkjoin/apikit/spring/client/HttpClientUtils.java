package org.forkjoin.apikit.spring.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.spring.utils.DateTimeUtils;
import org.forkjoin.apikit.spring.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zuoge85 on 15/5/30.
 */
public final class HttpClientUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
    public static final Charset CHARSET = Charset.forName("utf-8");

    public static void get(RequestHandler handler, String uri, Object... params) {
        Result result = JsonUtils.deserialize(
                get(handler, uri, null, params),
                Result.class
        );
        if (!result.isSuccess()) {
            throw new RuntimeException(result.getMsg());
        }
    }


    public static JsonNode getJson(RequestHandler handler, String uri, Object paramsObject, Object... params) {
        String json = get(handler, uri, paramsObject, params);
        return JsonUtils.deserialize(json);
    }

    public static String get(RequestHandler handler, String uri, Object paramsObject, Object... params) {
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            List<NameValuePair> paramsList = transform(paramsObject, params);

            String url = uriBuilder.setCharset(CHARSET).addParameters(paramsList).toString();
            log.debug("get url : {}", url);
            HttpResponse response = handler.request(Request.Get(url)).execute().returnResponse();
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("请求失败！" + response.toString());
            }
            log.debug("response:{}", response);
            return IOUtils.toString(response.getEntity().getContent(), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode postJson(RequestHandler handler, String uri, Object paramsObject, Object... params) {
        String json = post(handler, uri, paramsObject, params);
        return JsonUtils.deserialize(json);
    }

    public static String post(RequestHandler handler, String uri, Object paramsObject, Object... params) {
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            List<NameValuePair> paramsList = transform(paramsObject, params);

            if (log.isDebugEnabled()) {
                String url = uriBuilder.setCharset(CHARSET).addParameters(paramsList).toString();
                log.debug("post url : {}", url);
            }
            HttpResponse response = handler.request(Request.Post(uri)).bodyForm(
                    paramsList, CHARSET
            ).execute().returnResponse();

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("请求失败！" + response.toString());
            }
            HttpEntity entity = response.getEntity();
            String body = IOUtils.toString(entity.getContent(), CHARSET);
            log.debug("response:{},{}", response, body);
            return body;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static List<NameValuePair> transform(Object paramsObject, Object[] params) {
        try {
            List<NameValuePair> list = transform(paramsObject);

            if (params != null) {
                if (params.length % 2 > 0) {
                    throw new RuntimeException("参数必须是偶数");
                }
                for (int i = 0; i < params.length; i++) {
                    String key = String.valueOf(params[i]);
                    Object param = params[++i];
                    paramHandler(list, key, param);
                }
            }
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<NameValuePair> transform(Object paramsObject) {
        try {
            List<NameValuePair> list = new ArrayList<>();
            if (paramsObject != null) {
                objProperty(paramsObject, list);
            }
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void paramHandler(List<NameValuePair> list, String key, Object param) {
        if (param != null && param.getClass().isArray()) {
            Object[] paramArray = (Object[]) param;
            for (Object item : paramArray) {
                String value = String.valueOf(item);
                list.add(new BasicNameValuePair(key, value));
            }
        } else {
            String value = String.valueOf(param);
            list.add(new BasicNameValuePair(key, value));
        }
    }

    public static void objProperty(Object paramsObject, List<NameValuePair> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(paramsObject);
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            Object value = PropertyUtils.getProperty(paramsObject, descriptor.getName());
            if (value == null) {
                continue;
            }
            Object strValue;
            if (value instanceof Date) {
                strValue = DateTimeUtils.format((Date) value);
            } else if (value instanceof Class) {
                continue;
            } else {
                strValue = value;
            }
            paramHandler(list, descriptor.getName(), strValue);
        }
    }

    public interface RequestHandler {
        Request request(Request request);
    }
}
