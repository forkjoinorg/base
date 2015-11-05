package org.forkjoin.core.net.message.coder;

import org.forkjoin.core.net.NetPacketHandler;
import org.forkjoin.core.net.message.MessageProtocol;
import org.forkjoin.core.net.message.Packet;
import org.forkjoin.core.net.ChannelHandler;
import org.forkjoin.core.net.CrcEncryptChannelHandler;
import org.forkjoin.core.net.Session;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zuoge85
 */
public class CrcEncryptPacketDecoder extends ByteToMessageDecoder {
    //private final MessageFactory messageFactory;
    private final NetPacketHandler<?> handler;
    public CrcEncryptPacketDecoder(NetPacketHandler<?> handler) {
        this.handler = handler;
//		if (messageFactory == null) {
//			throw new NullPointerException("messageFactory");
//		}
//		this.messageFactory = messageFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> out) throws Exception {
        try {

            Session session = ctx.channel().attr(ChannelHandler.SESSION).get();
            if (handler.onIn(session, in)) {
                int readerIndex = in.readerIndex();
                int readableLen = in.readableBytes();
                if (readableLen >= MessageProtocol.LENGTH_BYTE_NUMS) {
                    int len = in.readUnsignedMedium();
//                    byte type = in.readByte();
                    if (readableLen >= (len + MessageProtocol.LENGTH_BYTE_NUMS)) {
                        CrcEncryptCoder coder = ctx.channel().attr(CrcEncryptChannelHandler.CRC_ENCRYPT).get();
                        Packet p = coder.read(len, in);
                        out.add(p);
                        return;
                    }
                }
                in.readerIndex(readerIndex);
            }
        } catch (Throwable t) {
            ctx.fireExceptionCaught(t);
        }
    }
}
