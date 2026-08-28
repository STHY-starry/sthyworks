package com.STHY.sthyworks.common.item;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;

import com.STHY.sthyworks.common.attribute.STHYAttributes;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.enchantment.EnchantmentLoader;
import com.STHY.sthyworks.common.potion.PotionLoader;
import com.STHY.sthyworks.common.util.ObsessionManager;
import com.STHY.sthyworks.common.util.sthyUtils;
import com.google.common.collect.Multimap;

import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;

public class VenerableShadow extends ItemArmor implements IVisDiscountGear, IWarpingGear, ISpecialArmor {

    public static final ItemArmor.ArmorMaterial VENERABLE_SHADOW = EnumHelper
        .addArmorMaterial("venerableShadow", 0, new int[] { 0, 0, 0, 0 }, 1);

    public VenerableShadow() {
        super(VENERABLE_SHADOW, 0, 1);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setUnlocalizedName("venerableShadow");
        this.setTextureName("sthyworks:venerableShadow");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "sthyworks:textures/models/armor/venerableShadow_layer.png";
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        super.addInformation(itemStack, entityPlayer, list, par4);
        list.add(
            EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount")
                + ": "
                + this.getVisDiscount(itemStack, entityPlayer, (Aspect) null)
                + "%");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (world.isRemote) return;
        if (sthyUtils.isAtSpecificTimes(world, 20, 0)) {
            Collection<PotionEffect> collection = player.getActivePotionEffects();
            collection.removeIf(potionEffect -> Potion.potionTypes[potionEffect.getPotionID()].isBadEffect());
            player.addPotionEffect(new PotionEffect(PotionLoader.soulAnnihilation.getId(), 40, 19));
        }
        if (!player.capabilities.allowFlying) {
            player.capabilities.allowFlying = true;
            player.sendPlayerAbilities();
        }
        if (!player.getEntityData()
            .getBoolean("VenerableArmorEquipped")) {
            player.getEntityData()
                .setBoolean("VenerableArmorEquipped", true);
        }
    }

    @Override
    public int getVisDiscount(ItemStack var1, EntityPlayer var2, Aspect var3) {
        return 15;
    }

    @Override
    public int getWarp(ItemStack var1, EntityPlayer var2) {
        return 20;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);
        multimap.put(
            STHYAttributes.controlOfMagic.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("da402834-c489-4582-a75d-dd6adcc1880d"),
                "VenerableShadow controlOfMagic 1",
                1.5D,
                1));
        multimap.put(
            SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("0335f371-8150-445e-869d-c72b374a0aed"),
                "VenerableShadow knockbackResistance 0",
                1.0D,
                0));
        return multimap;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
        int slot) {
        int obsession = 0;
        double controlOfMagic = 1.0D;
        int level = Math.min(10, EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.magicBoost.effectId, armor));
        if (player instanceof EntityPlayer) {
            obsession = ObsessionManager.getPlayerObsession((EntityPlayer) player);
            controlOfMagic = player.getEntityAttribute(STHYAttributes.controlOfMagic)
                .getAttributeValue();
        }
        double radio = Math.min(controlOfMagic * Math.pow(level + obsession, 3) / 3200000, 1.0D);
        return new ArmorProperties(1, radio, Integer.MAX_VALUE);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

    }
}
