package org.forkjoin.http.client;

import org.forkjoin.core.thread.ExecutorsUtils;
import org.forkjoin.spring.AccountHandlerInterceptor;
import org.forkjoin.spring.AccountRuntimeException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindException;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author zuoge85 on 15/6/18.
 */
public class AbstractMockHttpClientAdapter extends AbstractHttpClientAdapter {
    private static final Logger log = LoggerFactory.getLogger(AbstractMockHttpClientAdapter.class);

    private final ExecutorService asyncExecutor;

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    public AbstractMockHttpClientAdapter(String serverUrl) {
        super(serverUrl);

        asyncExecutor = ExecutorsUtils.newFixedThreadPool(32,
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        log.error("线程[{}],错误！{}", t, e.getMessage(), e);
                    }
                }, runnable -> {
                    Thread thread = new Thread(runnable);
                    thread.setName("用户处理异步线程池");
                    return thread;
                });
    }

    @PostConstruct
    private void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Override
    public <R> R request(String method, String uri, List<Map.Entry<String, Object>> form,
                         boolean isAccount, ResultCallback<R> callback) {
        try {
            return innerRequest(method, uri, form, isAccount, callback);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <R> Future<?> requestAsync(final String method, final String uri,
                                      final List<Map.Entry<String, Object>> form,
                                      final boolean isAccount, ResultCallback<R> callback) {
        try {
            /**
             * io 没真的异步，嘿嘿测试用
             */
            return asyncExecutor.submit(new Callable<R>() {
                @Override
                public R call() throws Exception {
                    return innerRequest(method, uri, form, isAccount, callback);
                }
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <R> R innerRequest(String method, String uri,
                               List<Map.Entry<String, Object>> form,
                               boolean isAccount, ResultCallback<R> callback) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = create(method,
                createUrl(uri));

        if (form != null) {
            for (Map.Entry<String, Object> entry : form) {
                requestBuilder.param(entry.getKey(),
                        AbstractMockHttpClientAdapter.this.toString(entry
                                .getValue()));
            }
        }

        if (isAccount) {
            requestBuilder.header(AccountHandlerInterceptor.ACCOUNT_TOKEN_HEADER_NAME, accountToken);
        }
        requestBuilder.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING);

        ResultActions perform = mvc.perform(requestBuilder);
        assertHeader(perform);
        MvcResult mvcResult = perform.andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        return callback.call(true, contentAsString, null);
    }

    private void assertHeader(ResultActions perform) throws Exception {
        perform.andExpect(content().encoding(ENCODING))
                .andExpect(
                        content().contentTypeCompatibleWith(
                                MediaType.APPLICATION_JSON))
                .andDo(new ResultHandler() {
                    @Override
                    public void handle(MvcResult mvcResult) throws Exception {
                        @SuppressWarnings("all")
                        Exception ex = mvcResult.getResolvedException();
                        if (ex != null && !(ex instanceof BindException)
                                && !(ex instanceof AccountRuntimeException)) {
                            log.error("错误！", ex.getMessage());
                            Assert.assertTrue(mvcResult.getResponse()
                                    .getContentAsString(), false);
                        }
                    }
                });
    }

    private MockHttpServletRequestBuilder create(String method, String uriString) {
        URI uri;
        try {
            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        switch (method.toUpperCase()) {
            case "POST":
                return post(uri);
            case "GET":
                return get(uri);
            case "PUT":
                return put(uri);
            case "DELETE":
                return delete(uri);
            case "PATCH":
                return patch(uri);
            default:
                throw new RuntimeException("不支持的类型：" + method);
        }
    }
}
