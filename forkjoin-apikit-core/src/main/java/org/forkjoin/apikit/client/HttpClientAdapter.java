package org.forkjoin.apikit.client;

import org.forkjoin.apikit.core.Result;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Future;


/**
 * http 请求实现接口，客户端自行实现
 * <b>ui适配器应该保证回调一定是在ui线程,服务器和测试代码则不需保证</b>
 *
 * @author zuoge85 on 15/6/15.
 */

/**
 * http 请求实现接口，客户端自行实现
 *
 * @author zuoge85 on 15/6/15.
 */
public interface HttpClientAdapter {
    /**
     * @param form 可能为空
     */
    <R extends Result<T>, T> R request(String method, String uri, List<Entry<String, Object>> form, Type type, boolean isAccount);

    /**
     * @param form 可能为空
     */
    <R extends Result<T>, T> Future<?> requestAsync(String method, String uri, List<Entry<String, Object>> form, Type type, boolean isAccount, Callback<R,T> callable);

    void close() throws IOException;
}