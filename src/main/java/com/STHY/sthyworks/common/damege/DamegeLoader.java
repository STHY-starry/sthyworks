package com.STHY.sthyworks.common.damege;

import net.minecraft.util.DamageSource;

public class DamegeLoader {

    public static DamageSource Pig;
    public static DamageSource Soul;

    public DamegeLoader() {

        Pig = new DamagePig();
        Soul = new DamageSoul();;

    }
}
