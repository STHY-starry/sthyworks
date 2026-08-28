package com.STHY.sthyworks.common.command;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandLoader {

    public CommandLoader(FMLServerStartingEvent event) {
        event.registerServerCommand(new ObsessionDebug());
    }
}
