package com.forkjoin.core.net;

import com.forkjoin.core.net.message.Message;

public interface NetMessageHandler<T> extends NetHandler<T>{
	void onMessage(Message msg) throws Exception;
}
