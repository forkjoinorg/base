package org.forkjoin.core.net;

import io.netty.channel.ChannelHandlerContext;

import org.forkjoin.core.net.message.Message;

@SuppressWarnings({"rawtypes","unchecked"})
public class MessageChannelHandler extends ChannelHandler<NetMessageHandler<?>,Message> {
	private NetMessageHandler<?> messageHandler;
	public MessageChannelHandler(NetMessageHandler<?> messageHandler) {
		super(messageHandler);
		this.messageHandler = messageHandler;
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception {
		if(log.isDebugEnabled()){
			log.debug("收到消息!{}", msg);
		}
		Session session = ctx.channel().attr(SESSION).get();
		msg.setSession(session);
		messageHandler.onMessage( msg);
	}
    
}
