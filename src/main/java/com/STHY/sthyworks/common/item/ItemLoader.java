package com.STHY.sthyworks.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemLoader {

    public static Item superPork = new SuperPork();
    public static Item pigPickaxe = new PigPickaxe();
    public static Item instructionManual = new InstructionManual();
    public static Item bucketMagic = new BucketMagic();
    public static Item immortalSword = new ImmortalSword();;

    public static ItemArmor dyeHelmet = new DyeArmor(0, "dyeHelmet", "dye");
    public static ItemArmor dyeChestplate = new DyeArmor(1, "dyeChestplate", "dye");
    public static ItemArmor dyeLeggings = new DyeArmor(2, "dyeLeggings", "dye");
    public static ItemArmor dyeBoots = new DyeArmor(3, "dyeBoots", "dye");

    public ItemLoader(FMLPreInitializationEvent event) {
        register(superPork, "superPork");
        register(pigPickaxe, "pigPickaxe");
        register(instructionManual, "instructionManual");
        register(bucketMagic, "bucketMagic");
        register(immortalSword, "immortalSword");

        register(dyeHelmet, "dyeHelmet");
        register(dyeChestplate, "dyeChestplate");
        register(dyeLeggings, "dyeLeggings");
        register(dyeBoots, "dyeBoots");
    }

    private static void register(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }
}
