package com.STHY.sthyworks.common.potion;

import net.minecraft.entity.EntityLivingBase;

import com.STHY.sthyworks.Config;

public class ReceivedDamageIncrease extends BasePotion {

    public ReceivedDamageIncrease() {
        super(Config.potionReceivedDamageIncrease, true, 0x90f5e4, "potion.receivedDamageIncrease", 0, -1, false);
    }

    @Override
    public void performEffect(EntityLivingBase living, int amplifier) {
        super.performEffect(living, amplifier);
    }
}
