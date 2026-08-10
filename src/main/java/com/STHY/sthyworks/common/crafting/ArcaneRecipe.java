package com.STHY.sthyworks.common.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.common.block.BlockLoader;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;

public class ArcaneRecipe {

    public static IArcaneRecipe arcaneGuguAltar;

    public static void registerArcaneRecipe() {
        arcaneGuguAltar = ThaumcraftApi.addArcaneCraftingRecipe(
            "GUGU_ALTAR",
            new ItemStack(BlockLoader.guguAltar),
            new AspectList().add(Aspect.AIR, 10)
                .add(Aspect.EARTH, 10)
                .add(Aspect.FIRE, 10)
                .add(Aspect.WATER, 10)
                .add(Aspect.ORDER, 10)
                .add(Aspect.ENTROPY, 10),
            "ABA",
            "ACA",
            "DDD",
            'A',
            Items.diamond,
            'B',
            Items.cake,
            'C',
            BlockLoader.guguBlock,
            'D',
            Blocks.stone);
    }

    //
}
