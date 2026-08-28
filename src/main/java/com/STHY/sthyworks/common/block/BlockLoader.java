package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import com.STHY.sthyworks.common.block.itemBlocks.ItemPlacedBelowBlock;
import com.STHY.sthyworks.sthyworks;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockLoader {

    public static Block guguBlock = new GuguBlock();
    public static Block guguAltar = new GuguAltarBlock();
    public static Block magicStone = new MagicStone();
    public static Block voidGateLandmark = new VoidGateLandmark();
    public static Block voidGate = new VoidGate();
    public static Block placedBelowBlock = new PlacedBelowBlock();
    public static Block ritualTable = new RitualTable();

    public static Block fluidMagic = new FluidMagicBlock();

    public BlockLoader() {
        registerBlock(guguBlock, "guguBlock");
        registerBlock(guguAltar, "guguAltar");
        registerBlock(magicStone, "magicStone");
        registerBlock(voidGateLandmark, "voidGateLandmark");
        registerBlock(voidGate, "voidGate");
        registerBlock(placedBelowBlock, ItemPlacedBelowBlock.class, "placedBelowBlock");
        registerBlock(ritualTable, "ritualTable");

        registerBlock(fluidMagic, "magic");
    }

    public void registerBlock(Block block, String name) {
        GameRegistry.registerBlock(block, sthyworks.MODID + ":" + name);
    }

    public void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, String name) {
        GameRegistry.registerBlock(block, itemBlockClass, sthyworks.MODID + ":" + name);
    }
}
