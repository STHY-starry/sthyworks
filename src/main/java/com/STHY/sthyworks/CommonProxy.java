package com.STHY.sthyworks;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.crafting.CraftingLoader;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.damege.DamegeLoader;
import com.STHY.sthyworks.common.enchantment.EnchantmentLoader;
import com.STHY.sthyworks.common.event.EventLoader;
import com.STHY.sthyworks.common.item.ItemLoader;
import com.STHY.sthyworks.common.potion.PotionLoader;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event);

        sthyworks.LOG.info("sthyworks: " + Tags.VERSION);
        new CreativeTabsLoader(event);
        new ItemLoader(event);
        new BlockLoader(event);
        new DamegeLoader();
        new PotionLoader(event);
    }

    public void init(FMLInitializationEvent event) {
        new CraftingLoader();
        new EnchantmentLoader();
        new EventLoader();

    }

    public void postInit(FMLPostInitializationEvent event) {}

    public void serverStarting(FMLServerStartingEvent event) {}
}
