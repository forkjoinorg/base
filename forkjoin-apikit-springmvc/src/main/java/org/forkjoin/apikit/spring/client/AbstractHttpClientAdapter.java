package org.forkjoin.apikit.spring.client;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.forkjoin.apikit.client.HttpClientAdapter;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.spring.AccountHandlerInterceptor;
import org.forkjoin.apikit.spring.utils.DateTimeUtils;
import org.forkjoin.apikit.spring.utils.JsonUtils;
import org.springframework.http.HttpHeaders;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/23.
 */
public abstract class AbstractHttpClientAdapter implements HttpClientAdapter {
    public static final String ENCODING = "UTF-8";
    public static final Charset CHARSET = Charset.forName(ENCODING);

    protected final String serverUrl;
    protected String accountToken;
    protected String suffix;

    public AbstractHttpClientAdapter(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    protected String createUrl(String uri) {
        return suffix == null ? serverUrl + uri : serverUrl + uri + "." + suffix;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getAccountToken() {
        return accountToken;
    }

    protected String toString(Object entry) {
        if (entry == null) {
            return null;
        }
        if (entry instanceof Date) {
            return DateTimeUtils.format((Date) entry);
        } else if (entry instanceof byte[]) {
            return Base64.encodeBase64URLSafeString((byte[]) entry);
        } else {
            return entry.toString();
        }
    }


    protected final HttpUriRequest createRequest(String method, List<Map.Entry<String, Object>> form, boolean isAccount, String url) {
        final boolean isPostForm = method.equalsIgnoreCase("POST") |
                method.equalsIgnoreCase("PUT") |
                method.equalsIgnoreCase("PATCH");

        RequestBuilder requestBuilder = RequestBuilder.create(method).setUri(url);
        if (isAccount && StringUtils.isNotEmpty(accountToken)) {
            requestBuilder.setHeader(AccountHandlerInterceptor.ACCOUNT_TOKEN_HEADER_NAME, accountToken);
        }
        requestBuilder.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        requestBuilder.setHeader(HttpHeaders.ACCEPT, "application/json;q=0.9,image/webp,*/*;q=0.8");

        if (form != null) {
            if (isPostForm) {
                List<BasicNameValuePair> collect = new ArrayList<>();
                for (Map.Entry<String, Object> entry : form) {
                    collect.add(new BasicNameValuePair(entry.getKey(), AbstractHttpClientAdapter.this.toString(entry.getValue())));
                }

                requestBuilder.setEntity(new UrlEncodedFormEntity(collect, CHARSET));
            } else {
                for (Map.Entry<String, Object> entry : form) {
                    requestBuilder.addParameter(entry.getKey(), toString(entry.getValue()));
                }
            }
        }
        return requestBuilder.build();
    }


    protected <T> Result<T> handlerResult(Type type, boolean isSuccess, String json, Exception ex) {
        if (isSuccess) {
            return JsonUtils.deserialize(json, type);
        } else {
            return Result.createError(ex);
        }
    }


    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
