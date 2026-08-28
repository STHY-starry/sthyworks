package com.STHY.sthyworks;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

    public static void synchronizeConfiguration(FMLPreInitializationEvent event) {
        Configuration configuration = new Configuration(event.getSuggestedConfigurationFile());

        enchantmentFireBurn = configuration
            .getInt("enchantmentFireBurn", Configuration.CATEGORY_GENERAL, 36, 0, 255, "Fire burn enchantment id. ");
        enchantmentMagicBoost = configuration.getInt(
            "enchantmentMagicBoost",
            Configuration.CATEGORY_GENERAL,
            37,
            0,
            255,
            "Magic boost enchantment id. ");

        potionReceivedDamageIncrease = configuration.getInt(
            "potionReceivedDamageIncrease",
            Configuration.CATEGORY_GENERAL,
            141,
            0,
            255,
            "ReceivedDamageIncrease potion id. ");
        potionSoulAnnihilation = configuration.getInt(
            "potionSoulAnnihilation",
            Configuration.CATEGORY_GENERAL,
            142,
            0,
            255,
            "Soul annihilation potion id. ");
        potionDeadlyPoison = configuration
            .getInt("potionDeadlyPoison", Configuration.CATEGORY_GENERAL, 143, 0, 255, "Deadly poison potion id. ");

        if (configuration.hasChanged()) {
            configuration.save();
        }

    }

    public static int enchantmentFireBurn = 36;
    public static int enchantmentMagicBoost = 37;

    public static int potionReceivedDamageIncrease = 141;
    public static int potionSoulAnnihilation = 142;
    public static int potionDeadlyPoison = 143;

}
