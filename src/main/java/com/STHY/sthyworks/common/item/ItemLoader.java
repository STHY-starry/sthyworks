package com.STHY.sthyworks.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

import com.STHY.sthyworks.common.item.etherealWing.AngelEtherealWing;
import com.STHY.sthyworks.common.item.etherealWing.DevilEtherealWing;
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
    public static Item angelEtherealWing = new AngelEtherealWing();
    public static Item devilEtherealWing = new DevilEtherealWing();
    public static Item guguSphere = new GuguSphere();
    public static Item sacredBlade = new SacredBlade();
    public static Item waterSageSpell = new WaterSageSpell();
    public static Item bladeAndShield = new BladeAndShield();

    public static ItemArmor dyeHelmet = new DyeArmor(0, "dyeHelmet", "dye");
    public static ItemArmor dyeChestplate = new DyeArmor(1, "dyeChestplate", "dye");
    public static ItemArmor dyeLeggings = new DyeArmor(2, "dyeLeggings", "dye");
    public static ItemArmor dyeBoots = new DyeArmor(3, "dyeBoots", "dye");
    public static ItemArmor venerableShadow = new VenerableShadow();

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
        register(angelEtherealWing, "angelEtherealWing");
        register(devilEtherealWing, "devilEtherealWing");
        register(guguSphere, "guguSphere");
        register(sacredBlade, "sacredBlade");
        register(waterSageSpell, "waterSageSpell");
        register(bladeAndShield, "bladeAndShield");

        register(dyeHelmet, "dyeHelmet");
        register(dyeChestplate, "dyeChestplate");
        register(dyeLeggings, "dyeLeggings");
        register(dyeBoots, "dyeBoots");
        register(venerableShadow, "venerableHelmet");
    }

    private static void register(Item item, String name) {
        GameRegistry.registerItem(item, sthyworks.MODID + ":" + name);
    }
}
