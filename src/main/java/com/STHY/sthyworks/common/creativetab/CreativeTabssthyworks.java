package com.STHY.sthyworks.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.STHY.sthyworks.common.item.ItemLoader;

public class CreativeTabssthyworks extends CreativeTabs {

    public CreativeTabssthyworks(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemLoader.superPork;
    }

}
