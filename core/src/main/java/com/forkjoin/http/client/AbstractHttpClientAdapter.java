package com.forkjoin.http.client;

import com.forkjoin.spring.AccountHandlerInterceptor;
import com.forkjoin.util.DateTimeUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;

/**
 * @author zuoge85 on 15/6/23.
 */
public abstract class AbstractHttpClientAdapter {
    public static final String ENCODING = "UTF-8";
    public static final Charset CHARSET = Charset.forName(ENCODING);

    protected final String serverUrl;
    protected String accountToken;

    public AbstractHttpClientAdapter(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    protected String createUrl(String uri) {
        return serverUrl + uri;
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
        if (isAccount) {
            requestBuilder.setHeader(AccountHandlerInterceptor.ACCOUNT_TOKEN_HEADER_NAME, accountToken);
        }
        requestBuilder.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");

        if (form != null) {
            if (isPostForm) {
                List<BasicNameValuePair> collect = form.stream()
                        .map(entry -> new BasicNameValuePair(entry.getKey(), AbstractHttpClientAdapter.this.toString(entry.getValue())))
                        .collect(toList());

                requestBuilder.setEntity(new UrlEncodedFormEntity(collect, CHARSET));
            } else {
                for (Map.Entry<String, Object> entry : form) {
                    requestBuilder.addParameter(entry.getKey(), toString(entry.getValue()));
                }
            }
        }
        return requestBuilder.build();
    }

    public abstract <R> R request(String method, String uri, List<Map.Entry<String, Object>> form, boolean isAccount, ResultCallback<R> callback);

    public abstract <R> Future<?> requestAsync(final String method, final String uri,
                                               final List<Map.Entry<String, Object>> form,
                                               final boolean isAccount, ResultCallback<R> callback);

    protected interface ResultCallback<R> {
        R call(boolean isSuccess, String json, Exception ex);
    }
}
