package com.STHY.sthyworks.common.creativetab;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader {

    public static CreativeTabssthyworks tabsthyworks;

    public CreativeTabsLoader(FMLPreInitializationEvent event) {
        tabsthyworks = new CreativeTabssthyworks("sthyworks");
    }
}
