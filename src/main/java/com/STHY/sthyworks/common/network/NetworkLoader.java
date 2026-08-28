package com.STHY.sthyworks.common.network;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkLoader {

    public static SimpleNetworkWrapper INSTANCE;

    public NetworkLoader() {
        INSTANCE = new SimpleNetworkWrapper("sthyworks");

        INSTANCE.registerMessage(PacketRequestObsession.Handler.class, PacketRequestObsession.class, 0, Side.SERVER);

        INSTANCE.registerMessage(PacketSyncObsession.Handler.class, PacketSyncObsession.class, 1, Side.CLIENT);
    }
}
