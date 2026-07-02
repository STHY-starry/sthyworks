package com.STHY.sthyworks.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.avaritia.render.ICosmicRenderItem;

public class StarrySky extends Item implements ICosmicRenderItem {

    private IIcon cosmicMask;

    public StarrySky() {
        super();
        setUnlocalizedName("starrySky");
        setTextureName("sthyworks:starrySky");
        setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        list.add(StatCollector.translateToLocal("item.starrySky.tooltips.line1"));
    }

    @Override
    public IIcon getMaskTexture(ItemStack stack, EntityPlayer player) {
        return cosmicMask;
    }

    @Override
    public float getMaskMultiplier(ItemStack stack, EntityPlayer player) {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register) {
        super.registerIcons(register);
        this.cosmicMask = register.registerIcon("sthyworks:starrySky_mask");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        worldIn.setWorldTime(18000);
        return itemStackIn;
    }
}
