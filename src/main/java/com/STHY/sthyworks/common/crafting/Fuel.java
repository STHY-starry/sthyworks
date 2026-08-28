package com.STHY.sthyworks.common.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.common.item.ItemLoader;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class Fuel {

    public static void registerFuel() {
        GameRegistry.registerFuelHandler(new IFuelHandler() {

            @Override
            public int getBurnTime(ItemStack fuel) {
                if (fuel.getItem() == Items.diamond) {
                    return 12800;
                }

                if (fuel.getItem() == ItemLoader.superPork) {
                    return 3200;
                }

                return 0;
            }
        });
    }
}
