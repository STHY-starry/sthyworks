package com.STHY.sthyworks.common.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class FluidLoader {

    public static Fluid magic = new Magic();

    public FluidLoader(FMLPreInitializationEvent event) {
        FluidRegistry.registerFluid(magic);
    }
}
