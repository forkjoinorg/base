package org.forkjoin.apikit.client;

import okhttp3.*;
import org.forkjoin.apikit.client.core.JsonConvert;
import org.forkjoin.apikit.client.core.TypeConvert;
import org.forkjoin.apikit.core.Result;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zuoge85@gmail.com on 2017/6/15.
 */
public class OkHttpClientAdapter extends AbstractHttpClientAdapter {
    public static final String ACCOUNT_TOKEN_HEADER_NAME = "authorization";
    protected OkHttpClient client = new OkHttpClient();

    protected String accountTokenHeaderName = ACCOUNT_TOKEN_HEADER_NAME;

    public OkHttpClientAdapter(String serverUrl) {
        super(serverUrl);
    }

    public OkHttpClientAdapter(String serverUrl, TypeConvert conversionService, JsonConvert jsonConvert) {
        super(serverUrl, conversionService, jsonConvert);
    }

    @Override
    public <R extends Result<T>, T> R request(String method, String uri, List<Map.Entry<String, Object>> form, Type type, boolean isAccount) {
        Request request = createRequest(method, form, isAccount, uri);
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            return OkHttpClientAdapter.this.<R, T>handlerResponse(response, type);
        } catch (IOException ex) {
            return OkHttpClientAdapter.this.<R, T>handlerResult(type, false, null, ex);
        }
    }

    @Override
    public <R extends Result<T>, T> Future<?> requestAsync(String method, String uri, List<Map.Entry<String, Object>> form, final Type type, boolean isAccount, final Callback<R, T> callable) {
        Request request = createRequest(method, form, isAccount, uri);
        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException ex) {
                R objectResult = OkHttpClientAdapter.this.<R, T>handlerResult(type, false, null, ex);
                callable.call(objectResult.getData(), objectResult);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                R objectResult = null;
                try {
                    objectResult = OkHttpClientAdapter.this.<R, T>handlerResponse(response, type);
                } finally {
                    if (objectResult != null) {
                        callable.call(objectResult.getData(), objectResult);
                    }
                    response.close();
                }
            }

        });
        return new CallFuture(call);
    }


    private <R extends Result<T>, T> R handlerResponse(Response response, final Type type) {
        try {
            ResponseBody body = response.body();
            if (body == null) {
                return this.<R, T>handlerResult(type, false, null, new RuntimeException("body 为空, ResponseBody:" + body));
            }
            MediaType mediaType = body.contentType();
            if (mediaType == null) {
                return this.<R, T>handlerResult(type, false, null, new RuntimeException("mediaType 为空, ResponseBody:" + body));
            }
            if (mediaType.toString().startsWith("application/json")) {
                String content = body.string();
                return this.<R, T>handlerResult(type, true, content, null);
            } else {
                String content = body.string();
                return this.<R, T>handlerResult(type, false, content, new RuntimeException("错误的类型:" + mediaType + ",content:" + content));
            }
        } catch (Exception ex) {
            return this.<R, T>handlerResult(type, false, null, ex);
        }
    }

    protected final Request createRequest(String method, List<Map.Entry<String, Object>> form, boolean isAccount, String uri) {
        final boolean isPostForm = method.equalsIgnoreCase("POST") |
                method.equalsIgnoreCase("PUT") |
                method.equalsIgnoreCase("PATCH");

        HttpUrl.Builder builder = HttpUrl.parse(createUrl(uri)).newBuilder();
        RequestBody body = null;

        if (form != null) {
            if (isPostForm) {
                FormBody.Builder formBuild = new FormBody.Builder();
                for (Map.Entry<String, Object> entry : form) {
                    formBuild.add(entry.getKey(), this.toString(entry.getValue()));
                }
                body = formBuild.build();
            } else {
                for (Map.Entry<String, Object> entry : form) {
                    builder.addQueryParameter(entry.getKey(), toString(entry.getValue()));
                }
            }
        }else if(isPostForm){
            body = new FormBody.Builder().build();
        }

        Request.Builder requestBuilder = new Request.Builder();

        if (isAccount && accountToken != null) {
            requestBuilder.addHeader(accountTokenHeaderName, accountToken);
        }
        requestBuilder.addHeader("accept-language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        requestBuilder.addHeader("accept", "application/json;q=0.9,image/webp,*/*;q=0.8");

        return requestBuilder
                .url(builder.build())
                .method(method, body)
                .build();
    }


    @Override
    public void close() throws IOException {
        client.dispatcher().executorService().shutdown();   //清除并关闭线程池
        client.connectionPool().evictAll();                 //清除并关闭连接池
        client.cache().close();                             //清除cache
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public String getAccountTokenHeaderName() {
        return accountTokenHeaderName;
    }

    public void setAccountTokenHeaderName(String accountTokenHeaderName) {
        this.accountTokenHeaderName = accountTokenHeaderName;
    }

    protected class CallFuture<V> implements Future<V> {
        protected Call call;

        public CallFuture(Call call) {
            this.call = call;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            call.cancel();
            return true;
        }

        @Override
        public boolean isCancelled() {
            return call.isCanceled();
        }

        @Override
        public boolean isDone() {
            return call.isExecuted();
        }

        @Override
        public V get() throws InterruptedException, ExecutionException {
            throw new java.lang.AbstractMethodError("未实现");
        }

        @Override
        public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            throw new java.lang.AbstractMethodError("未实现");
        }
    }
}
