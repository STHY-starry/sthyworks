package com.STHY.sthyworks;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

    public static void synchronizeConfiguration(FMLPreInitializationEvent event) {
        Configuration configuration = new Configuration(event.getSuggestedConfigurationFile());

        pigBlockBurnTime = configuration
            .getInt("pigBlockBurnTime", Configuration.CATEGORY_GENERAL, 3200, 0, 1000000, "burning time of a PigBlock.");

        enchantmentFireBurn = configuration
            .getInt("enchantmentFireBurn", Configuration.CATEGORY_GENERAL, 36, 0, 255, "Fire burn enchantment id. ");

        potionFallProtection = configuration
            .getInt("potionFallProtection", Configuration.CATEGORY_GENERAL, 24, 0, 31, "Fall protection potion id. ");
        potionSoulAnnihilation = configuration
            .getInt("potionSoulAnnihilation", Configuration.CATEGORY_GENERAL, 25, 0, 31, "Soul annihilation potion id. ");

        if (configuration.hasChanged()) {
            configuration.save();
        }

    }

    public static int pigBlockBurnTime = 3200;

    public static int enchantmentFireBurn = 36;

    public static int potionFallProtection = 24;
    public static int potionSoulAnnihilation = 25;

}
