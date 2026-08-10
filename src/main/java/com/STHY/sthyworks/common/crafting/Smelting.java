package com.STHY.sthyworks.common.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.common.item.ItemLoader;

import cpw.mods.fml.common.registry.GameRegistry;

public class Smelting {

    public static void registerSmelting() {
        GameRegistry.addSmelting(ItemLoader.superPork, new ItemStack(Items.nether_star), 66.6F);
    }
}
