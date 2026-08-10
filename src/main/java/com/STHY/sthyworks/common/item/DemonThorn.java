package com.STHY.sthyworks.common.item;

import static com.STHY.sthyworks.common.util.ItemStoreEntityUUID.clearStoredEntityUUID;
import static com.STHY.sthyworks.common.util.ItemStoreEntityUUID.getItemStoredEntity;
import static com.STHY.sthyworks.common.util.ItemStoreEntityUUID.hasStoredEntityUUID;
import static com.STHY.sthyworks.common.util.ItemStoreEntityUUID.storeEntityUUID;

import java.util.List;
import java.util.UUID;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.enchantment.EnchantmentLoader;
import com.STHY.sthyworks.common.entity.withoutEgg.DemonThornProjectile;
import com.STHY.sthyworks.common.potion.PotionLoader;
import com.google.common.collect.Multimap;

public class DemonThorn extends ItemSword {

    public static final Item.ToolMaterial DemonThornMaterial = EnumHelper
        .addToolMaterial("DemonThornMaterial", 0, 0, 10.0F, 5.0F, 22);

    public DemonThorn() {
        super(DemonThornMaterial);
        this.setUnlocalizedName("demonThorn");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:demonThorn");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        list.add(StatCollector.translateToLocal("item.demonThorn.tooltips.line1"));
        list.add(StatCollector.translateToLocal("item.demonThorn.tooltips.line2"));
        list.add(StatCollector.translateToLocal("item.demonThorn.tooltips.line3"));
        list.add(StatCollector.translateToLocal("item.demonThorn.tooltips.line4"));
        list.add(StatCollector.translateToLocal("item.demonThorn.tooltips.line5"));
        list.add(StatCollector.translateToLocal("item.demonThorn.tooltips.line6"));
    }

    @Override
    public boolean isItemTool(ItemStack itemStack) {
        return this.getItemStackLimit(itemStack) == 1;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity)
                .addPotionEffect(new PotionEffect(PotionLoader.receivedDamageIncrease.getId(), 1, 4));
        }
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (hasStoredEntityUUID(itemStackIn)) {
                DemonThornProjectile projectile = getItemStoredEntity(worldIn, itemStackIn, DemonThornProjectile.class);
                if (projectile != null && !projectile.isDead) {
                    if (player.isSneaking()) {
                        projectile.setDead();
                    } else {
                        double x = projectile.posX;
                        double y = projectile.posY;
                        double z = projectile.posZ;
                        projectile.setDead();
                        player.setPositionAndUpdate(x, y, z);
                    }
                }
                clearStoredEntityUUID(itemStackIn);
            } else {
                if (!player.isSneaking()) {
                    Vec3 look = player.getLookVec();
                    DemonThornProjectile projectile = new DemonThornProjectile(worldIn, player);
                    projectile.setBaseDamage(9.0F);
                    projectile.setMaxPenetration(3);
                    projectile.setMagicBoostLevel(
                        EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.magicBoost.effectId, itemStackIn));
                    projectile.setThrowableHeading(look.xCoord, look.yCoord, look.zCoord, 1.6F, 1.0F);
                    worldIn.spawnEntityInWorld(projectile);
                    storeEntityUUID(itemStackIn, projectile.getUniqueID());
                }
            }
        }
        return super.onItemRightClick(itemStackIn, worldIn, player);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);
        multimap.put(
            SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("12ef2b91-06c3-4655-b3d4-9ff051307646"),
                "DemonThorn maxHealth 1",
                -0.2D,
                1));
        return multimap;
    }
}
