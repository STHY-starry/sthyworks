package com.STHY.sthyworks.common.potion;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.potion.Potion;

public class PotionLoader {

    public static Potion fallProtection;
    public static Potion soulAnnihilation;

    public PotionLoader(FMLPreInitializationEvent event){
        fallProtection = new FallProtection();
        soulAnnihilation = new SoulAnnihilation();
    }
}
