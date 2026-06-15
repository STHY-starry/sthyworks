package com.STHY.sthyworks.common.damege;

import net.minecraft.util.DamageSource;

public class DamageSoul extends DamageSource {

    public DamageSoul() {
        super("soul");
        this.setDamageIsAbsolute();
        this.setDamageAllowedInCreativeMode();
    }
}
