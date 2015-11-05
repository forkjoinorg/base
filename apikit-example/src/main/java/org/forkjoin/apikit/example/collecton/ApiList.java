package org.forkjoin.apikit.example.collecton;

import com.forkjoin.api.Message;

import java.util.List;


/**
 * api的list集合,用于封装list数据
 * 
 * @author zuoge85 on 15/8/11.
 */
@Message
public class ApiList<T> {
	List<T> list;
}
