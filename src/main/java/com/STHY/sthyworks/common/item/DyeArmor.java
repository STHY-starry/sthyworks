package com.STHY.sthyworks.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DyeArmor extends ItemArmor {

    public static final ItemArmor.ArmorMaterial DYE_ARMOR = EnumHelper
        .addArmorMaterial("dye", 10, new int[] { 1, 3, 2, 1 }, 10);

    private String itemTexture;
    private String armorTexture;

    public DyeArmor(int ArmorType, String itemTexture, String armorTexture) {
        super(DYE_ARMOR, 0, ArmorType);
        this.itemTexture = itemTexture;
        this.armorTexture = armorTexture;
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setUnlocalizedName(itemTexture);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("sthyworks:" + itemTexture);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (slot == 2) return "sthyworks:textures/models/armor/" + armorTexture + "_layer_2.png";
        else return "sthyworks:textures/models/armor/" + armorTexture + "_layer_1.png";
    }
}
