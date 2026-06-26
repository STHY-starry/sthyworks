package com.STHY.sthyworks.common.fluid;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidLoader {
    public static Fluid magic = new Magic();

    public FluidLoader(FMLPreInitializationEvent event) {
        FluidRegistry.registerFluid(magic);
    }
}
