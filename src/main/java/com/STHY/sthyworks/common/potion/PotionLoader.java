package com.STHY.sthyworks.common.potion;

import net.minecraft.potion.Potion;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PotionLoader {

    public static Potion fallProtection;
    public static Potion soulAnnihilation;

    public PotionLoader(FMLPreInitializationEvent event) {
        fallProtection = new FallProtection();
        soulAnnihilation = new SoulAnnihilation();
    }
}
