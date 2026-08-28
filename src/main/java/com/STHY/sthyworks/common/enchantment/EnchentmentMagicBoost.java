package com.STHY.sthyworks.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.Config;

public class EnchentmentMagicBoost extends Enchantment {

    public EnchentmentMagicBoost() {
        super(Config.enchantmentMagicBoost, 2, EnumEnchantmentType.all);
        this.setName("magicBoost");
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 5 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack);
    }
}
