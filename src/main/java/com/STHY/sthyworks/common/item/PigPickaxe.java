package com.STHY.sthyworks.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class PigPickaxe extends ItemPickaxe {

    public static final Item.ToolMaterial PIG = EnumHelper.addToolMaterial("PIG", 2, 500, 6.0F, 2.0F, 14);

    public PigPickaxe() {
        super(PIG);
        this.setUnlocalizedName("pigPickaxe");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:pigPickaxe");
    }
}
