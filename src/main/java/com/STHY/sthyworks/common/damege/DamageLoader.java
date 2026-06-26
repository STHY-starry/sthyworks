package com.STHY.sthyworks.common.damege;

import net.minecraft.util.DamageSource;

public class DamageLoader {

    public static DamageSource Pig;
    public static DamageSource Soul;

    public DamageLoader() {

        Pig = new DamagePig();
        Soul = new DamageSoul();;

    }
}
