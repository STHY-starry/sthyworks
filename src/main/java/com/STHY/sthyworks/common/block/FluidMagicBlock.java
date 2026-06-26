package com.STHY.sthyworks.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.fluid.FluidLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluidMagicBlock extends BlockFluidClassic {

    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;

    public FluidMagicBlock() {
        super(FluidLoader.magic, Material.water);
        this.setBlockName("magic");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.stillIcon = register.registerIcon("sthyworks:magic_still");
        this.flowingIcon = register.registerIcon("sthyworks:magic_flow");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return (side != 0 && side != 1) ? this.flowingIcon : this.stillIcon;
    }
}
