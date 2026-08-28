package com.STHY.sthyworks.common.network;

import com.STHY.sthyworks.common.util.ObsessionManager;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketRequestObsession implements IMessage {

    public PacketRequestObsession() {}

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketRequestObsession, PacketSyncObsession> {

        @Override
        public PacketSyncObsession onMessage(PacketRequestObsession message, MessageContext ctx) {
            int obsession = ObsessionManager.getPlayerObsession(ctx.getServerHandler().playerEntity);
            return new PacketSyncObsession(obsession);
        }
    }
}
