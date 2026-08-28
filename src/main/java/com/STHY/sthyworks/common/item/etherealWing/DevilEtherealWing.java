package com.STHY.sthyworks.common.item.etherealWing;

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

public class DevilEtherealWing extends EtherealWingBase {

    public DevilEtherealWing() {
        super();
        this.setUnlocalizedName("devilEtherealWing");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:devilEtherealWing");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        list.add(StatCollector.translateToLocal("item.devilEtherealWing.line1"));
        list.add(StatCollector.translateToLocal("item.devilEtherealWing.line2"));
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (count != 1) return;
        if (player.worldObj.isRemote) return;

        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound devilTagCopy = (NBTTagCompound) stack.getTagCompound()
            .copy();
        ItemStack angelStack = new ItemStack(ItemLoader.angelEtherealWing);

        if (devilTagCopy.hasKey("angelTag")) {
            angelStack.setTagCompound(devilTagCopy.getCompoundTag("angelTag"));
            devilTagCopy.removeTag("angelTag");
        }
        if (!angelStack.hasTagCompound()) {
            angelStack.setTagCompound(new NBTTagCompound());
        }
        angelStack.getTagCompound()
            .setTag("devilTag", devilTagCopy);

        player.inventory.mainInventory[player.inventory.currentItem] = angelStack;

        player.removePotionEffect(Potion.wither.getId());
        AngelEtherealWing.angelEffect(player);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slotIndex, boolean isHeld) {
        if (!isHeld) return;
        if (!(entityIn instanceof EntityLivingBase)) return;

        EntityLivingBase entityLivingBase = (EntityLivingBase) entityIn;
        if (sthyUtils.isAtSpecificTimes(worldIn, 200, 1)) {
            devilEffect(entityLivingBase);
        }
    }

    public static void devilEffect(EntityLivingBase entityLivingBase) {
        entityLivingBase.addPotionEffect(new PotionEffect(Potion.wither.getId(), 200, 0));
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);

        multimap.put(
            SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("eea3b4aa-6097-42f4-8734-3d0de30938d1"),
                "DevilEtherealWing movementSpeed 1",
                0.2D,
                1));
        return multimap;
    }
}
