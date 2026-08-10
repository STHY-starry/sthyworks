package com.STHY.sthyworks.common.item;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import com.STHY.sthyworks.client.key.KeyLoader;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.google.common.collect.Multimap;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.common.BaubleItemBase;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;

public class IvoryNecklace extends BaubleItemBase implements IWarpingGear, IVisDiscountGear {

    public IvoryNecklace() {
        super();
        this.setUnlocalizedName("ivoryNecklace");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:ivoryNecklace");
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemstack) {
        return new String[] { BaubleExpandedSlots.amuletType };
    }

    // 兼容旧版的方法
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean debug) {
        KeyBinding toggleTooltipsDisplayKey = KeyLoader.toggleTooltipsDisplay;
        super.addInformation(stack, player, list, debug);
        if (!Keyboard.isKeyDown(toggleTooltipsDisplayKey.getKeyCode())) {
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.routine.line1"));
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.routine.line2"));
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.routine.line3"));
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.routine.line4"));
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.routine.line5"));
            list.add(
                String.format(
                    StatCollector.translateToLocal("key.sthyworks.toggleTooltipsDisplay.tooltips"),
                    GameSettings.getKeyDisplayString(toggleTooltipsDisplayKey.getKeyCode())));
        } else {
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.toggle.line1"));
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.toggle.line2"));
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.toggle.line3"));
            list.add(StatCollector.translateToLocal("item.ivoryNecklace.tooltips.toggle.line4"));
        }
    }

    @Override
    public boolean isItemTool(ItemStack itemStack) {
        return this.getItemStackLimit(itemStack) == 1;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase player) {
        super.onWornTick(itemStack, player);
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase player) {
        player.getAttributeMap()
            .applyAttributeModifiers(itemStack.getAttributeModifiers());
    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase player) {
        player.getAttributeMap()
            .removeAttributeModifiers(itemStack.getAttributeModifiers());
    }

    @Override
    public int getWarp(ItemStack itemStack, EntityPlayer player) {
        return 10;
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer player, Aspect aspect) {
        return 5;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);
        multimap.put(
            SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("30de4439-d7c2-4644-9e42-1efb1b1b3685"),
                "IvoryNecklace attackDamage 2",
                0.2D,
                2));
        multimap.put(
            SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("ac64b542-42e7-49da-81ca-0f6b1ffd0d6d"),
                "IvoryNecklace knockbackResistance 2",
                0.2D,
                2));
        multimap.put(
            SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("65e32739-8e11-42a0-878c-1710c0ffabc2"),
                "IvoryNecklace maxHealth 2",
                0.2D,
                2)

        );
        multimap.put(
            SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("4361e58e-ea47-4b92-af79-1a468ef520c6"),
                "IvoryNecklace movementSpeed 2",
                0.2D,
                2));
        return multimap;
    }
}
