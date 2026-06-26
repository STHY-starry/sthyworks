package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockLoader {

    public static Block pigBlock = new pigBlock();
    public static Block guguBlock = new guguBlock();

    public static Block fluidMagic = new FluidMagicBlock();

    public BlockLoader(FMLPreInitializationEvent event) {
        GameRegistry.registerBlock(pigBlock, "pigBlock");
        GameRegistry.registerBlock(guguBlock, "guguBlock");

        GameRegistry.registerBlock(fluidMagic, "magic");
    }
}
