package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class GuguBlock extends Block {

    public GuguBlock() {
        super(Material.rock);
        this.setBlockName("guguBlock");
        this.setHardness(0.5F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockTextureName("sthyworks:guguBlock");
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }
}
