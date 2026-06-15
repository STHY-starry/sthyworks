package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class pigBlock extends Block {

    public pigBlock() {
        super(Material.rock);
        this.setBlockName("pigBlock");
        this.setHardness(1.5F);
        this.setResistance(10000.0F);
        this.setStepSound(Block.soundTypePiston);
        this.setBlockTextureName("sthyworks:pigBlock");
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }
}
