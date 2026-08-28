package com.STHY.sthyworks.common.damege;

import net.minecraft.util.DamageSource;

public class DamageLoader {

    public static DamageSource Pig;
    public static DamageSource Soul;
    public static DamageSource Ordinary;

    public DamageLoader() {

        Pig = new DamageSource("pig").setDamageAllowedInCreativeMode();
        Soul = new DamageSource("soul").setDamageIsAbsolute()
            .setDamageAllowedInCreativeMode();
        Ordinary = new DamageSource("ordinary");
    }
}
