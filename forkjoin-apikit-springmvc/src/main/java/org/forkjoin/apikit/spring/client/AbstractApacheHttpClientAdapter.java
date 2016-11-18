package org.forkjoin.apikit.spring.client;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zuoge85 on 15/6/23.
 */
public abstract class AbstractApacheHttpClientAdapter extends AbstractHttpClientAdapter {

    public static final String CHARSET_PARAMETER_NAME = "charset";
    private final CloseableHttpAsyncClient httpClient;// = HttpAsyncClients.createDefault();

    public AbstractApacheHttpClientAdapter(String serverUrl, String proxy, int port) {
        super(serverUrl);
        if (proxy != null) {
            httpClient = HttpAsyncClientBuilder.create().setProxy(new HttpHost(proxy, port)).build();
        } else {
            httpClient = HttpAsyncClientBuilder.create().build();
        }
        httpClient.start();
    }

    public AbstractApacheHttpClientAdapter(String serverUrl) {
        this(serverUrl, null, 0);
    }

    public <R> R request(String method, String uri, List<Map.Entry<String, Object>> form, boolean isAccount, final ResultCallback<R> callback) {
        try {
            final AtomicReference<R> resultRef = new AtomicReference<>(null);
            final CountDownLatch latch = new CountDownLatch(1);
            requestAsync(method, uri, form, isAccount, new ResultCallback<Object>() {
                @Override
                public Object call(boolean isSuccess, String json, Exception ex) {
                    R r = callback.call(isSuccess, json, ex);
                    resultRef.set(r);
                    latch.countDown();
                    return r;
                }
            }).get();
            latch.await();

            return resultRef.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <R> Future<?> requestAsync(
            String method, String uri, List<Map.Entry<String, Object>> form,
            boolean isAccount, final ResultCallback<R> callback
    ) {
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
                        callback.call(true, content, null);
                    } else {
                        content = IOUtils.toString(entity.getContent(), charset);
                        callback.call(false, content, new RuntimeException("错误的类型:" + contentType + ",content:" + content));
                    }
                } catch (Exception ex) {
                    callback.call(false, null, ex);
                }
            }

            @Override
            public void failed(Exception ex) {
                callback.call(false, null, ex);
            }

            @Override
            public void cancelled() {
                callback.call(false, null, new RuntimeException("连接被取消"));
            }
        });
    }

    public void close() throws IOException {
        httpClient.close();
    }
}
