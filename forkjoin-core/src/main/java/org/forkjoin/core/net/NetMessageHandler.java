package org.forkjoin.core.net;

import org.forkjoin.core.net.message.Message;

public interface NetMessageHandler<T> extends NetHandler<T>{
	void onMessage(Message msg) throws Exception;
}
