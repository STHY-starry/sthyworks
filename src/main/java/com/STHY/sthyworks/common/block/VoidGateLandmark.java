package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class VoidGateLandmark extends Block {

    public VoidGateLandmark() {
        super(Material.rock);
        this.setBlockName("voidGateLandmark");
        this.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
        this.setHardness(3.0F);
        this.setResistance(8.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockTextureName("sthyworks:voidGateLandmark");
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
