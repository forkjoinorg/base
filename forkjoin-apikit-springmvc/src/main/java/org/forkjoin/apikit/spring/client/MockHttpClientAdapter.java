package org.forkjoin.apikit.spring.client;

import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.client.Callback;
import org.forkjoin.apikit.client.core.JsonConvert;
import org.forkjoin.apikit.client.core.TypeConvert;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.spring.AccountHandlerInterceptor;
import org.forkjoin.apikit.spring.AccountRuntimeException;
import org.forkjoin.apikit.spring.I18nValidationException;
import org.forkjoin.apikit.spring.utils.ExecutorsUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author zuoge85 on 15/6/18.
 */
public class MockHttpClientAdapter extends org.forkjoin.apikit.client.AbstractHttpClientAdapter {
    private static final Logger log = LoggerFactory.getLogger(MockHttpClientAdapter.class);

    private final ExecutorService asyncExecutor;

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    public MockHttpClientAdapter(String serverUrl, TypeConvert typeConvert, JsonConvert jsonConvert) {
        super(serverUrl, typeConvert, jsonConvert);

        asyncExecutor = ExecutorsUtils.newFixedThreadPool(32,
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        log.error("线程[{}],错误！{}", t, e.getMessage(), e);
                    }
                }, new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable runnable) {
                        Thread thread = new Thread(runnable);
                        thread.setName("用户处理异步线程池");
                        return thread;
                    }
                });
    }


    public MockHttpClientAdapter(String serverUrl) {
        this(serverUrl, null, null);
    }


    @PostConstruct
    private void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Override
    public <R extends Result<T>, T> R request(String method, String uri, List<Map.Entry<String, Object>> form, Type type, boolean isAccount) {
        try {
            return this.<R, T>innerRequest(method, uri, form, isAccount, type, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <R extends Result<T>, T> Future<?> requestAsync(
            final String method, final String uri,
            final List<Map.Entry<String, Object>> form,
            final Type type, final boolean isAccount,
            final Callback<R, T> callable
    ) {
        try {
            /**
             * io 没真的异步，嘿嘿测试用
             */
            return asyncExecutor.submit(new Callable<R>() {
                @Override
                public R call() throws Exception {
                    return innerRequest(method, uri, form, isAccount, type, callable);
                }
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <R extends Result<T>, T> R innerRequest(String method, String uri,
                                                    List<Map.Entry<String, Object>> form,
                                                    boolean isAccount, Type type, Callback<R, T> callable) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = create(method,
                createUrl(uri));

        if (form != null) {
            for (Map.Entry<String, Object> entry : form) {
                requestBuilder.param(entry.getKey(),
                        MockHttpClientAdapter.this.toString(entry
                                .getValue()));
            }
        }

        if (isAccount && StringUtils.isNotEmpty(accountToken)) {
            requestBuilder.header(AccountHandlerInterceptor.ACCOUNT_TOKEN_HEADER_NAME, accountToken);
        }
        requestBuilder.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(ENCODING);

        ResultActions perform = mvc.perform(requestBuilder);
        assertHeader(perform);
        MvcResult mvcResult = perform.andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        R objectResult = this.<R, T>handlerResult(type, true, contentAsString, null);
        if (callable != null) {
            callable.call(objectResult.getData(), objectResult);
        }
        return objectResult;
    }

    private void assertHeader(ResultActions perform) throws Exception {
        perform
//                .andExpect(content().encoding(ENCODING))
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(new ResultHandler() {
                    @Override
                    public void handle(MvcResult mvcResult) throws Exception {
                        @SuppressWarnings("all")
                        Exception ex = mvcResult.getResolvedException();
                        if (ex != null && !(ex instanceof BindException)
                                && !(ex instanceof AccountRuntimeException) && !(ex instanceof I18nValidationException)) {
                            log.error("错误！", ex.getMessage());
                            Assert.assertTrue(mvcResult.getResponse().getContentAsString(), false);
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

    @Override
    @PreDestroy
    public void close() throws IOException {
        asyncExecutor.shutdownNow();
    }
}
