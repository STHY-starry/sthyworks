package com.STHY.sthyworks.common.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.Config;
import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.item.ItemLoader;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingLoader {

    public CraftingLoader() {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe() {
        GameRegistry.addShapedRecipe(
            new ItemStack(ItemLoader.superPork),
            new Object[] { "###", "#*#", "###", '#', Items.gold_ingot, '*', Items.porkchop });
    }

    private static void registerSmelting() {
        GameRegistry.addSmelting(ItemLoader.superPork, new ItemStack(Items.nether_star), 66.6F);
    }

    private static void registerFuel() {
        GameRegistry.registerFuelHandler(new IFuelHandler() {

            @Override
            public int getBurnTime(ItemStack fuel) {
                if (fuel.getItem() == Items.diamond) {
                    return 12800;
                }

                if (fuel.getItem() == ItemLoader.superPork) {
                    return 3200;
                }

                if (fuel.getItem() == Item.getItemFromBlock(BlockLoader.pigBlock)) {

                    return Config.pigBlockBurnTime;
                }

                return 0;
            }
        });
    }
}
