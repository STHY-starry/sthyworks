package com.STHY.sthyworks.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

import com.STHY.sthyworks.common.item.EtherealWing.AngelEtherealWing;
import com.STHY.sthyworks.common.item.EtherealWing.DevilEtherealWing;
import com.STHY.sthyworks.sthyworks;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemLoader {

    public static Item superPork = new SuperPork();
    public static Item pigPickaxe = new PigPickaxe();
    public static Item instructionManual = new InstructionManual();
    public static Item bucketMagic = new BucketMagic();
    public static Item immortalSword = new ImmortalSword();
    public static Item demonThorn = new DemonThorn();
    public static Item starrySky = new StarrySky();
    public static Item milkTea = new MilkTea();
    public static Item imperialSkySword = new ImperialSkySword();
    public static Item ivoryNecklace = new IvoryNecklace();
    public static Item enchantedMirror = new EnchantedMirror();
    public static Item angelEtherealWing = new AngelEtherealWing();
    public static Item devilEtherealWing = new DevilEtherealWing();
    public static Item pathogenesis = new Pathogenesis();
    public static Item guguSphere = new GuguSphere();
    public static Item sacredBlade = new SacredBlade();
    // public static Item gift = new Gift();
    public static Item waterSageSpell = new WaterSageSpell();
    public static Item item2048 = new Item2048();

    public static ItemArmor dyeHelmet = new DyeArmor(0, "dyeHelmet", "dye");
    public static ItemArmor dyeChestplate = new DyeArmor(1, "dyeChestplate", "dye");
    public static ItemArmor dyeLeggings = new DyeArmor(2, "dyeLeggings", "dye");
    public static ItemArmor dyeBoots = new DyeArmor(3, "dyeBoots", "dye");

    public ItemLoader() {
        register(superPork, "superPork");
        register(pigPickaxe, "pigPickaxe");
        register(instructionManual, "instructionManual");
        register(bucketMagic, "bucketMagic");
        register(immortalSword, "immortalSword");
        register(demonThorn, "demonThorn");
        register(starrySky, "starrySky");
        register(milkTea, "milkTea");
        register(imperialSkySword, "imperialSkySword");
        register(ivoryNecklace, "ivoryNecklace");
        register(enchantedMirror, "enchantedMirror");
        register(angelEtherealWing, "angelEtherealWing");
        register(devilEtherealWing, "devilEtherealWing");
        register(pathogenesis, "pathogenesis");
        register(guguSphere, "guguSphere");
        register(sacredBlade, "sacredBlade");
        // register(gift, "gift");
        register(waterSageSpell, "waterSageSpell");
        register(item2048, "item2048");

        register(dyeHelmet, "dyeHelmet");
        register(dyeChestplate, "dyeChestplate");
        register(dyeLeggings, "dyeLeggings");
        register(dyeBoots, "dyeBoots");
    }

    private static void register(Item item, String name) {
        GameRegistry.registerItem(item, sthyworks.MODID + ":" + name);
    }
}
