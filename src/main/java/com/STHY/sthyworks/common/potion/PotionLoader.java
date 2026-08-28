package com.STHY.sthyworks.common.potion;

import net.minecraft.potion.Potion;

public class PotionLoader {

    public static Potion receivedDamageIncrease;
    public static Potion soulAnnihilation;

    public PotionLoader() {
        receivedDamageIncrease = new ReceivedDamageIncrease();
        soulAnnihilation = new SoulAnnihilation();
    }
}
