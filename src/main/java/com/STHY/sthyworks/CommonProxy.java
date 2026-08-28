package com.STHY.sthyworks;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.command.CommandLoader;
import com.STHY.sthyworks.common.crafting.CraftingLoader;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.damege.DamageLoader;
import com.STHY.sthyworks.common.enchantment.EnchantmentLoader;
import com.STHY.sthyworks.common.entity.EntityLoader;
import com.STHY.sthyworks.common.event.EventLoader;
import com.STHY.sthyworks.common.fluid.FluidLoader;
import com.STHY.sthyworks.common.item.ItemLoader;
import com.STHY.sthyworks.common.network.NetworkLoader;
import com.STHY.sthyworks.common.potion.PotionLoader;
import com.STHY.sthyworks.common.thaumcraft.AspectLoader;
import com.STHY.sthyworks.common.thaumcraft.ResearchLoader;
import com.STHY.sthyworks.common.tileentity.TileEntityLoader;
import com.STHY.sthyworks.common.worldgen.WorldGeneratorLoader;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event);

        sthyworks.LOG.info("sthyworks: " + Tags.VERSION);
        new NetworkLoader();
        new AspectLoader();
        new CreativeTabsLoader();
        new FluidLoader();
        new ItemLoader();
        new TileEntityLoader();
        new BlockLoader();
        new DamageLoader();
        new PotionLoader();
        new EntityLoader();
    }

    public void init(FMLInitializationEvent event) {
        new CraftingLoader();
        new EnchantmentLoader();
        new EventLoader();
        new WorldGeneratorLoader();
    }

    public void postInit(FMLPostInitializationEvent event) {
        new ResearchLoader();
    }

    public void serverStarting(FMLServerStartingEvent event) {
        new CommandLoader(event);
    }
}
