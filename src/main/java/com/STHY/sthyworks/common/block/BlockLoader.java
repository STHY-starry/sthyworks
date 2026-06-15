package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockLoader {

    public static Block pigBlock = new pigBlock();
    public static Block guguBlock = new guguBlock();

    public BlockLoader(FMLPreInitializationEvent event) {
        register(pigBlock, "pigBlock");
        register(guguBlock, "guguBlock");
    }

    private static void register(Block block, String name) {
        GameRegistry.registerBlock(block, name);
    }
}
