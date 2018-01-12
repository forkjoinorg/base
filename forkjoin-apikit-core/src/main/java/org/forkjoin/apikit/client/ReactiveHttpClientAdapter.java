package org.forkjoin.apikit.client;

import org.forkjoin.apikit.core.Result;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map.Entry;


/*
 * http 请求实现接口，客户端自行实现
 * <b>ui适配器应该保证回调一定是在ui线程,服务器和测试代码则不需保证</b>
 *
 * @author zuoge85 on 15/6/15.
 */
public interface ReactiveHttpClientAdapter {
    /**
     * @param form 可能为空
     */
    <R extends Result<T>, T> Mono<R> requestResult(String method, String uri, List<Entry<String, Object>> form, Type type, boolean isAccount);

    <T> Mono<T> request(String method, String uri, List<Entry<String, Object>> form, Type type, boolean isAccount);

    void close() throws IOException;
}