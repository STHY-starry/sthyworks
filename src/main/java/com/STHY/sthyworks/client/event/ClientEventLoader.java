package com.STHY.sthyworks.client.event;

import cpw.mods.fml.common.FMLCommonHandler;

public class ClientEventLoader {

    public ClientEventLoader() {
        FMLCommonHandler.instance()
            .bus()
            .register(new KeyEvent());
    }
}
