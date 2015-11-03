package com.forkjoin.core.net.message;

import java.io.IOException;

import com.forkjoin.core.io.Input;
import com.forkjoin.core.io.Output;
import com.forkjoin.core.io.ProtocolException;
import com.forkjoin.core.net.Session;

/**
 * 
 * @author zuoge85
 *
 */
public interface Message {
//	private static final byte messageType = 0;
//	private static final byte messageId = 0;
	
//	public Message(byte messageType, byte messageId) {
//		this.messageType = messageType;
//		this.messageId = messageId;
//	}
	
	abstract void decode(Input in)  throws IOException, ProtocolException ;
	abstract void encode(Output out)  throws IOException, ProtocolException ;

	abstract int getMessageType();
	abstract int getMessageId();

	abstract <T> Session<T> getSession();

	abstract <T> void setSession(Session<T> session) ;
}
