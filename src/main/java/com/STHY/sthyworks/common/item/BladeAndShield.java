package com.STHY.sthyworks.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;

import com.STHY.sthyworks.api.IItemShield;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

public class BladeAndShield extends ItemSword implements IItemShield {

    @SideOnly(Side.CLIENT)
    private IIcon iconBlade;
    @SideOnly(Side.CLIENT)
    private IIcon iconShield;

    public BladeAndShield() {
        super(ToolMaterial.IRON);
        this.setUnlocalizedName("bladeAndShield");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon("sthyworks:bladeAndShield");
        iconBlade = register.registerIcon("sthyworks:bladeAndShield_blade");
        iconShield = register.registerIcon("sthyworks:bladeAndShield_shield");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem == stack) return iconShield;
        return iconBlade;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        player.addPotionEffect(new PotionEffect(Potion.field_76444_x.getId(), 2, 0));
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        tag.setBoolean("isUsing", true);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {

    }

    @Override
    public void onOwnerHurt(ItemStack stack, EntityPlayer player, DamageSource source, float amount) {
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }
    }
}
