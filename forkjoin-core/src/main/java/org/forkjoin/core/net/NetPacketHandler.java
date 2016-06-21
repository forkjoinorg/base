package org.forkjoin.core.net;

import org.forkjoin.core.net.message.Packet;

public interface NetPacketHandler<T> extends NetHandler<T> {
    /**
     * 需要使用完毕msg后回收msg内的buf
     *
     * @param msg
     * @throws Exception
     */
    void onPacket(Packet msg) throws Exception;

}
