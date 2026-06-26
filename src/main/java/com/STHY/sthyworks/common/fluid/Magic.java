package com.STHY.sthyworks.common.fluid;

import net.minecraftforge.fluids.Fluid;

public class Magic extends Fluid{

    public Magic() {
        super("magic");
        this.setUnlocalizedName("magic");
        this.setDensity(500);
        this.setViscosity(1500);
        this.setTemperature(2000);
        this.setLuminosity(8);
        this.setGaseous(true);
    }

}
