package com.STHY.sthyworks.common.enchantment;

import net.minecraft.enchantment.Enchantment;

import com.STHY.sthyworks.Config;
import com.STHY.sthyworks.sthyworks;

public class EnchantmentLoader {

    public static Enchantment fireBurn;
    public static Enchantment magicBoost;

    public EnchantmentLoader() {
        fireBurn = registerEnchantment(new EnchantmentFireBurn(), Config.enchantmentFireBurn);
        magicBoost = registerEnchantment(new EnchentmentMagicBoost(), Config.enchantmentMagicBoost);
    }

    private static Enchantment registerEnchantment(Enchantment enchantment, int configId) {
        return registerEnchantment(enchantment, configId, true);
    }

    private static Enchantment registerEnchantment(Enchantment enchantment, int configId, boolean addToBookList) {
        try {
            if (addToBookList) {
                Enchantment.addToBookList(enchantment);
            }
            return enchantment;
        } catch (Exception e) {
            sthyworks.LOG.error(
                "Duplicate or illegal enchantment id: {}, the registry of class '{}' will be skipped. ",
                configId,
                enchantment.getClass()
                    .getName());
            return null;
        }
    }
}
