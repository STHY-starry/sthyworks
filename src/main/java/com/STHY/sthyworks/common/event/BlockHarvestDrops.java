package com.STHY.sthyworks.common.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;

import com.STHY.sthyworks.common.enchantment.EnchantmentLoader;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BlockHarvestDrops {

    @SubscribeEvent
    public void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event) {

        // Fire Burn
        if (!event.world.isRemote && event.harvester != null) {

            ItemStack itemStack = event.harvester.getHeldItem();
            if (EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.fireBurn.effectId, itemStack) > 0) {
                for (int i = 0; i < event.drops.size(); i++) {
                    ItemStack stack = event.drops.get(i);
                    ItemStack newStack = FurnaceRecipes.smelting()
                        .getSmeltingResult(stack);
                    if (newStack != null) {
                        newStack = newStack.copy();
                        newStack.stackSize = stack.stackSize;
                        event.drops.set(i, newStack);
                    }
                }
            }

        }

    }
}
