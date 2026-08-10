package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;

import com.STHY.sthyworks.sthyworks;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockLoader {

    public static Block pigBlock = new PigBlock();
    public static Block guguBlock = new GuguBlock();
    public static Block guguAltar = new GuguAltarBlock();
    public static Block game2048Block = new Game2048Block();
    public static Block magicStone = new MagicStone();

    public static Block fluidMagic = new FluidMagicBlock();

    public BlockLoader() {
        registerBlock(pigBlock, "pigBlock");
        registerBlock(guguBlock, "guguBlock");
        registerBlock(guguAltar, "guguAltar");
        registerBlock(game2048Block, "game2048Block");
        registerBlock(magicStone, "magicStone");

        registerBlock(fluidMagic, "magic");
    }

    public void registerBlock(Block block, String name) {
        GameRegistry.registerBlock(block, sthyworks.MODID + ":" + name);
    }
}
