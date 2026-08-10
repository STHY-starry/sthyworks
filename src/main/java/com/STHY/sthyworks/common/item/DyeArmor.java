package com.STHY.sthyworks.common.item;

import java.util.UUID;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.google.common.collect.Multimap;

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
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("sthyworks:" + itemTexture);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (slot == 2) return "sthyworks:textures/models/armor/" + armorTexture + "_layer_2.png";
        else return "sthyworks:textures/models/armor/" + armorTexture + "_layer_1.png";
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);
        switch (this.armorType) {
            case 0:
                multimap.put(
                    SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(),
                    new AttributeModifier(
                        UUID.fromString("7cbfe7e8-9bdf-427d-9754-30ec8d9b5f90"),
                        "DyeHelmet maxHealth 0",
                        2.0D,
                        0));
                break;
            case 1:
                multimap.put(
                    SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
                    new AttributeModifier(
                        UUID.fromString("a0357033-a53c-43e5-93ae-3bd6fbd55adf"),
                        "DyeChestplate attackDamage 0",
                        2.0D,
                        0));
                multimap.put(
                    SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
                    new AttributeModifier(
                        UUID.fromString("b8d94a5d-a180-41f7-bcf3-01c2e8113155"),
                        "DyeChestplate attackDamage 1",
                        0.1D,
                        1));
                break;
            case 2:
                multimap.put(
                    SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(),
                    new AttributeModifier(
                        UUID.fromString("fea71c30-7974-484c-b4b1-e97983976a85"),
                        "DyeLeggings knockbackResistance 0",
                        0.1D,
                        0));
                break;
            case 3:
                multimap.put(
                    SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(),
                    new AttributeModifier(
                        UUID.fromString("90dbc595-ba01-4bf9-af68-0ce47b9f95bb"),
                        "DyeBoots movementSpeed 0",
                        0.2D,
                        0));
                break;
        }
        return multimap;
    }
}
