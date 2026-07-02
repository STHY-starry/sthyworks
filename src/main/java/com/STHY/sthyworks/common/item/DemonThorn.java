package com.STHY.sthyworks.common.item;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.entity.projectile.DemonThornProjectile;
import com.STHY.sthyworks.common.potion.PotionLoader;

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
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity)
                .addPotionEffect(new PotionEffect(PotionLoader.receivedDamageIncrease.getId(), 60, 4));
        }
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (hasStoredProjectile(itemStackIn)) {
                DemonThornProjectile projectile = getStoredProjectile(worldIn, itemStackIn);
                if (projectile != null && !projectile.isDead) {
                    if (player.isSneaking()) {
                        projectile.setDead();
                        clearStoredProjectile(itemStackIn);
                    } else {
                        double x = projectile.posX;
                        double y = projectile.posY;
                        double z = projectile.posZ;
                        projectile.setDead();
                        clearStoredProjectile(itemStackIn);
                        player.setPositionAndUpdate(x, y, z);
                    }
                } else {
                    clearStoredProjectile(itemStackIn);
                }
            } else {
                Vec3 look = player.getLookVec();
                DemonThornProjectile projectile = new DemonThornProjectile(worldIn, player);
                projectile.setDamage(9.0F);
                projectile.setMaxPenetration(3);
                projectile.setThrowableHeading(look.xCoord, look.yCoord, look.zCoord, 1.6F, 1.0F);
                worldIn.spawnEntityInWorld(projectile);
                storeProjectileUUID(itemStackIn, projectile.getUniqueID());
            }
        }
        return super.onItemRightClick(itemStackIn, worldIn, player);
    }

    public void storeProjectileUUID(ItemStack itemStackIn, UUID uuid) {
        if (!itemStackIn.hasTagCompound()) {
            itemStackIn.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = itemStackIn.getTagCompound();
        tag.setLong("UUID_MOST_SIG_TAG", uuid.getMostSignificantBits());
        tag.setLong("UUID_LEAST_SIG_TAG", uuid.getLeastSignificantBits());
    }

    public UUID getStoredProjectileUUID(ItemStack itemStackIn) {
        if (!itemStackIn.hasTagCompound()) {
            return null;
        }
        NBTTagCompound tag = itemStackIn.getTagCompound();
        if (!tag.hasKey("UUID_MOST_SIG_TAG", 4) || !tag.hasKey("UUID_LEAST_SIG_TAG", 4)) {
            return null;
        }
        long mostSig = tag.getLong("UUID_MOST_SIG_TAG");
        long leastSig = tag.getLong("UUID_LEAST_SIG_TAG");
        return new UUID(mostSig, leastSig);
    }

    public boolean hasStoredProjectile(ItemStack stack) {
        return getStoredProjectileUUID(stack) != null;
    }

    public void clearStoredProjectile(ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            tag.removeTag("UUID_MOST_SIG_TAG");
            tag.removeTag("UUID_LEAST_SIG_TAG");
        }
    }

    public DemonThornProjectile getStoredProjectile(World world, ItemStack stack) {
        UUID uuid = getStoredProjectileUUID(stack);
        if (uuid == null || world == null || world.isRemote) {
            return null;
        }

        for (Entity entity : world.getLoadedEntityList()) {
            if (entity instanceof DemonThornProjectile) {
                DemonThornProjectile projectile = (DemonThornProjectile) entity;
                if (projectile.getUniqueID()
                    .equals(uuid)) {
                    if (!projectile.isDead) {
                        return projectile;
                    } else {
                        clearStoredProjectile(stack);
                        return null;
                    }
                }
            }
        }
        clearStoredProjectile(stack);
        return null;
    }
}
