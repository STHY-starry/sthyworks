package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class PigBlock extends Block {

    public PigBlock() {
        super(Material.rock);
        this.setBlockName("pigBlock");
        this.setHardness(1.5F);
        this.setResistance(100.0F);
        this.setStepSound(Block.soundTypePiston);
        this.setBlockTextureName("sthyworks:pigBlock");
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }
}
