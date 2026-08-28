package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlacedBelowBlock extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public PlacedBelowBlock() {
        super(Material.rock);
        this.setBlockName("placedBelowBlock");
        this.setHardness(1.0F);
        this.setResistance(0.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockTextureName("stone");
        this.setHarvestLevel("pickaxe", 0);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        super.registerBlockIcons(reg);
        this.icon = reg.registerIcon("sthyworks:placedBelowBlock");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 0 || side == 1) {
            return this.blockIcon;
        } else {
            return icon;
        }
    }
}
