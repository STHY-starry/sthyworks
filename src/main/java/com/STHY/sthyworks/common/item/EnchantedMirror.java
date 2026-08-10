package com.STHY.sthyworks.common.item;

import net.minecraft.item.Item;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class EnchantedMirror extends Item {

    public EnchantedMirror() {
        this.setUnlocalizedName("enchantedMirror");
        this.setTextureName("sthyworks:enchantedMirror");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setMaxStackSize(1);
    }
}
