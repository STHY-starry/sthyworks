package com.STHY.sthyworks.common.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.common.item.ItemLoader;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipe {

    public static void registerRecipe() {
        GameRegistry.addShapedRecipe(
            new ItemStack(ItemLoader.superPork),
            "AAA",
            "ABA",
            "AAA",
            'A',
            Items.gold_ingot,
            'B',
            Items.porkchop);
    }
}
