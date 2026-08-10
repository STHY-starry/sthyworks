package com.STHY.sthyworks.common.damege;

import net.minecraft.util.DamageSource;

public class DamageDeadlyPoison extends DamageSource {

    public DamageDeadlyPoison() {
        super("deadlyPoison");
        this.setDamageIsAbsolute();
        this.setDamageBypassesArmor();
    }

}
