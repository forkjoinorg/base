package org.forkjoin.apikit.client;

/**
 * @author zuoge85@gmail.com on 2016/11/23.
 */
public interface Callback<T> {
    void call(Result<T> t);
}
