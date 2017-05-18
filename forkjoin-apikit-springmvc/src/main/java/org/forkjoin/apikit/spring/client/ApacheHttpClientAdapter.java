package org.forkjoin.apikit.spring.client;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.forkjoin.apikit.JsonConvert;
import org.forkjoin.apikit.client.Callback;
import org.forkjoin.apikit.core.Result;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zuoge85 on 15/6/23.
 */
public class ApacheHttpClientAdapter extends AbstractHttpClientAdapter {

    public static final String CHARSET_PARAMETER_NAME = "charset";
    private final CloseableHttpAsyncClient httpClient;// = HttpAsyncClients.createDefault();

    public ApacheHttpClientAdapter(
            String serverUrl, String proxy, int port,
            ConversionService conversionService, JsonConvert jsonConvert
    ) {
        super(serverUrl, conversionService, jsonConvert);
        if (proxy != null) {
            httpClient = HttpAsyncClientBuilder.create().setProxy(new HttpHost(proxy, port)).build();
        } else {
            httpClient = HttpAsyncClientBuilder.create().build();
        }
        httpClient.start();
    }

    public ApacheHttpClientAdapter(String serverUrl, String proxy, int port) {
        this(serverUrl, proxy, port, null, null);
    }

    public ApacheHttpClientAdapter(String serverUrl, ConversionService conversionService) {
        this(serverUrl, null, 0, conversionService, null);
    }

    public ApacheHttpClientAdapter(String serverUrl, ConversionService conversionService, JsonConvert jsonConvert) {
        this(serverUrl, null, 0, conversionService, jsonConvert);
    }

    public ApacheHttpClientAdapter(String serverUrl) {
        this(serverUrl, null, 0, null, null);
    }

    public <T> Result<T> request(String method, String uri, List<Map.Entry<String, Object>> form, Type type, boolean isAccount) {
        try {
            final AtomicReference<Result<T>> resultRef = new AtomicReference<>(null);
            final CountDownLatch latch = new CountDownLatch(1);
            requestAsync(method, uri, form, type, isAccount, new Callback<T>() {
                public void call(Result<T> t) {
                    resultRef.set(t);
                    latch.countDown();
                }
            }).get();
            latch.await();

            return resultRef.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Future<?> requestAsync(String method, String uri, List<Map.Entry<String, Object>> form, final Type type, boolean isAccount, final Callback<T> callable) {
        final String url = createUrl(uri);
        HttpUriRequest request = createRequest(method, form, isAccount, url);

        return httpClient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                String content;
                try {
                    HttpEntity entity = result.getEntity();
                    String contentType = entity.getContentType().getValue();

                    String charset;
                    if (entity.getContentEncoding() == null) {
                        charset = entity.getContentType().getElements()[0].getParameterByName(CHARSET_PARAMETER_NAME).getValue();
                    } else {
                        charset = entity.getContentEncoding().getValue();
                    }

                    if (contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
                        content = IOUtils.toString(entity.getContent(), charset);
                        Result<T> objectResult = handlerResult(type, true, content, null);
                        callable.call(objectResult);
                    } else {
                        content = IOUtils.toString(entity.getContent(), charset);
                        Result<T> objectResult = handlerResult(type, false, content, new RuntimeException("错误的类型:" + contentType + ",content:" + content));
                        callable.call(objectResult);
                    }
                } catch (Exception ex) {
                    Result<T> objectResult = handlerResult(type, false, null, ex);
                    callable.call(objectResult);
                }
            }

            @Override
            public void failed(Exception ex) {
                Result<T> objectResult = handlerResult(type, false, null, ex);
                callable.call(objectResult);
            }

            @Override
            public void cancelled() {
                Result<T> objectResult = handlerResult(type, false, null, new RuntimeException("连接被取消"));
                callable.call(objectResult);
            }
        });
    }

    public void close() throws IOException {
        httpClient.close();
    }
}
