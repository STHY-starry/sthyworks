package com.STHY.sthyworks.common.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.common.item.ItemLoader;

import fox.spiteful.avaritia.items.LudicrousItems;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

public class Infusion {

    public static InfusionRecipe infusionStarrySky;

    public static void registerInfusionRecipe() {
        infusionStarrySky = ThaumcraftApi.addInfusionCraftingRecipe(
            "STARRY_SKY",
            new ItemStack(ItemLoader.starrySky),
            5,
            new AspectList().add(Aspect.DARKNESS, 10)
                .add(Aspect.LIGHT, 5)
                .add(Aspect.MAGIC, 10),
            new ItemStack(Items.nether_star),
            new ItemStack[] { new ItemStack(LudicrousItems.resource, 1, 5), new ItemStack(Blocks.glass),
                new ItemStack(LudicrousItems.resource, 1, 5), new ItemStack(Blocks.glass) });
    }
}
