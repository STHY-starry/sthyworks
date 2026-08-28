package com.STHY.sthyworks.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketSyncObsession implements IMessage {

    private int obsession;

    public PacketSyncObsession() {}

    public PacketSyncObsession(int obsession) {
        this.obsession = obsession;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.obsession = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.obsession);
    }

    public static class Handler implements IMessageHandler<PacketSyncObsession, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncObsession message, MessageContext ctx) {
            ClientCacheData.obsession = message.obsession;
            return null;
        }
    }
}
