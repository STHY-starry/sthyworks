package com.STHY.sthyworks.common.enchantment;

import net.minecraft.enchantment.Enchantment;

import com.STHY.sthyworks.Config;
import com.STHY.sthyworks.sthyworks;

public class EnchantmentLoader {

    public static Enchantment fireBurn;

    public EnchantmentLoader() {
        try {
            fireBurn = new EnchantmentFireBurn();
            Enchantment.addToBookList(fireBurn);
        } catch (Exception e) {
            sthyworks.LOG.error(
                "Duplicate or illegal enchantment id: {}, the registry of class '{}' will be skipped. ",
                Config.enchantmentFireBurn,
                EnchantmentFireBurn.class.getName());
        }
    }
}
