package com.STHY.sthyworks.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.STHY.sthyworks.api.IItemShield;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slotIndex, boolean isHeld) {
        if (worldIn.isRemote) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        if (tag.getInteger("parryTime") > 0) {
            tag.setInteger("parryTime", tag.getInteger("parryTime") - 1);
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (player.worldObj.isRemote) return;
        if (count % 5 == 0) {
            player.addPotionEffect(new PotionEffect(Potion.field_76444_x.getId(), 10, 0));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (!itemStackIn.hasTagCompound()) {
            itemStackIn.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = itemStackIn.getTagCompound();
        tag.setInteger("parryTime", 0);
        return super.onItemRightClick(itemStackIn, worldIn, player);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
        if (world.isRemote) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        int usedTime = this.getMaxItemUseDuration(stack) - count;
        if (usedTime > 10) {
            tag.setInteger("parryTime", 5);
        }
    }

    @Override
    public void onOwnerHurt(ItemStack stack, EntityPlayer player, LivingHurtEvent event) {
        if (player.worldObj.isRemote) return;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        if (tag.getInteger("parryTime") > 0) {
            tag.setInteger("parryTime", 0);
            event.ammount *= 0.1F;
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 100, 2));
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 100, 1));
        }
    }
}
