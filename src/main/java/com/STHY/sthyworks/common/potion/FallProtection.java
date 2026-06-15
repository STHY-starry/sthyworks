package com.STHY.sthyworks.common.potion;

import net.minecraft.entity.EntityLivingBase;

import com.STHY.sthyworks.Config;

public class FallProtection extends BasePotion {

    public FallProtection() {
        super(Config.potionFallProtection, false, 0x90f5e4, "potion.fallProtection", 0, -1, false);
    }

    @Override
    public void performEffect(EntityLivingBase living, int amplifier) {
        super.performEffect(living, amplifier);
    }
}
