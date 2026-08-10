package com.STHY.sthyworks.common.item.EtherealWing;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.item.ItemLoader;
import com.STHY.sthyworks.common.util.sthyUtils;
import com.google.common.collect.Multimap;

public class AngelEtherealWing extends EtherealWingBase {

    public AngelEtherealWing() {
        super();
        this.setUnlocalizedName("angelEtherealWing");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:angelEtherealWing");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        list.add(StatCollector.translateToLocal("item.angelEtherealWing.line1"));
        list.add(StatCollector.translateToLocal("item.angelEtherealWing.line2"));
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (count != 1) return;
        if (player.worldObj.isRemote) return;

        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound angelTagCopy = (NBTTagCompound) stack.getTagCompound()
            .copy();
        ItemStack devilStack = new ItemStack(ItemLoader.devilEtherealWing);

        if (angelTagCopy.hasKey("devilTag")) {
            devilStack.setTagCompound(angelTagCopy.getCompoundTag("devilTag"));
            angelTagCopy.removeTag("devilTag");
        }
        if (!devilStack.hasTagCompound()) {
            devilStack.setTagCompound(new NBTTagCompound());
        }
        devilStack.getTagCompound()
            .setTag("angelTag", angelTagCopy);

        player.inventory.mainInventory[player.inventory.currentItem] = devilStack;

        player.removePotionEffect(Potion.regeneration.getId());
        DevilEtherealWing.devilEffect(player);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slotIndex, boolean isHeld) {
        if (!isHeld) return;
        if (!(entityIn instanceof EntityLivingBase)) return;

        EntityLivingBase entityLivingBase = (EntityLivingBase) entityIn;
        if (sthyUtils.isAtSpecificTimes(worldIn, 200, 0)) {
            angelEffect(entityLivingBase);
        }
    }

    public static void angelEffect(EntityLivingBase entityLivingBase) {
        entityLivingBase.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 200, 0));
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);

        multimap.put(
            SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("3de861d1-be16-40be-a8ee-5b3f6af4a21d"),
                "AngelEtherealWing maxHealth 1",
                0.2D,
                1));
        return multimap;
    }
}
