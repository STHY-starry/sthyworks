package com.STHY.sthyworks.common.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidLoader {

    public static Fluid magic = new Magic();

    public FluidLoader() {
        FluidRegistry.registerFluid(magic);
    }
}
