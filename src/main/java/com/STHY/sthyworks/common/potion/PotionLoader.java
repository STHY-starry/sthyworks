package com.STHY.sthyworks.common.potion;

import net.minecraft.potion.Potion;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PotionLoader {

    public static Potion receivedDamageIncrease;
    public static Potion soulAnnihilation;

    public PotionLoader(FMLPreInitializationEvent event) {
        receivedDamageIncrease = new ReceivedDamageIncrease();
        soulAnnihilation = new SoulAnnihilation();
    }
}
