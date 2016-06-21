package org.forkjoin.apikit.example.collecton;


import org.forkjoin.apikit.core.Message;

/**
 * api的list集合,用于封装list数据
 *
 * @author zuoge85 on 15/8/11.
 */
@Message
public class ApiList<T> {
    T[] list;
}
