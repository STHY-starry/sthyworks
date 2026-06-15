package com.STHY.sthyworks.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.Config;

public class EnchantmentFireBurn extends Enchantment {

    public EnchantmentFireBurn() {
        super(Config.enchantmentFireBurn, 5, EnumEnchantmentType.digger);
        this.setName("fireBurn");
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 5;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench.effectId != fortune.effectId && ench.effectId != silkTouch.effectId;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack);
    }
}
