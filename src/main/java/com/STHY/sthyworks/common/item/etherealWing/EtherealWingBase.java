package com.STHY.sthyworks.common.item.etherealWing;

import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class EtherealWingBase extends ItemSword {

    public static final Item.ToolMaterial EtherealWingMaterial = EnumHelper
        .addToolMaterial("EtherealWingMaterial", 0, 0, 10.0F, 4.0F, 22);

    public EtherealWingBase() {
        super(EtherealWingMaterial);
    }

    @Override
    public boolean isItemTool(ItemStack itemStack) {
        return this.getItemStackLimit(itemStack) == 1;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.bow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 24;
    }
}
