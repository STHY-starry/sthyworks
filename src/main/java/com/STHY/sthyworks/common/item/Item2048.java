package com.STHY.sthyworks.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Item2048 extends Item {

    @SideOnly(Side.CLIENT)
    IIcon[] icons = new IIcon[18];

    public Item2048() {
        setUnlocalizedName("item2048");
        setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setMaxStackSize(1);
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
    }

    @Override
    public void registerIcons(IIconRegister register) {
        icons[0] = register.registerIcon("sthyworks:item2048_0");
        icons[1] = register.registerIcon("sthyworks:item2048_2");
        icons[2] = register.registerIcon("sthyworks:item2048_4");
        icons[3] = register.registerIcon("sthyworks:item2048_8");
        icons[4] = register.registerIcon("sthyworks:item2048_16");
        icons[5] = register.registerIcon("sthyworks:item2048_32");
        icons[6] = register.registerIcon("sthyworks:item2048_64");
        icons[7] = register.registerIcon("sthyworks:item2048_128");
        icons[8] = register.registerIcon("sthyworks:item2048_256");
        icons[9] = register.registerIcon("sthyworks:item2048_512");
        icons[10] = register.registerIcon("sthyworks:item2048_1024");
        icons[11] = register.registerIcon("sthyworks:item2048_2048");
        icons[12] = register.registerIcon("sthyworks:item2048_4096");
        icons[13] = register.registerIcon("sthyworks:item2048_8192");
        icons[14] = register.registerIcon("sthyworks:item2048_16384");
        icons[15] = register.registerIcon("sthyworks:item2048_32768");
        icons[16] = register.registerIcon("sthyworks:item2048_65536");
        icons[17] = register.registerIcon("sthyworks:item2048_131072");
    }
}
