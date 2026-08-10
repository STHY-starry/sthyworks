package com.STHY.sthyworks.common.util;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemStoreEntityUUID {

    public static void storeEntityUUID(ItemStack itemStackIn, UUID uuid) {
        if (!itemStackIn.hasTagCompound()) {
            itemStackIn.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = itemStackIn.getTagCompound();
        tag.setLong("UUID_MOST_SIG_TAG", uuid.getMostSignificantBits());
        tag.setLong("UUID_LEAST_SIG_TAG", uuid.getLeastSignificantBits());
    }

    public static UUID getStoredEntityUUID(ItemStack itemStackIn) {
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

    public static boolean hasStoredEntityUUID(ItemStack stack) {
        return getStoredEntityUUID(stack) != null;
    }

    public static void clearStoredEntityUUID(ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            tag.removeTag("UUID_MOST_SIG_TAG");
            tag.removeTag("UUID_LEAST_SIG_TAG");
        }
    }

    public static <T extends Entity> T getItemStoredEntity(World world, ItemStack stack, Class<T> entityClass) {
        UUID uuid = getStoredEntityUUID(stack);
        if (uuid == null || world == null || world.isRemote) {
            return null;
        }

        for (Entity entity : world.getLoadedEntityList()) {
            if (entityClass.isInstance(entity)) {
                if (entity.getUniqueID()
                    .equals(uuid)) {
                    if (!entity.isDead) {
                        return entityClass.cast(entity);
                    } else {
                        clearStoredEntityUUID(stack);
                        return null;
                    }
                }
            }
        }
        clearStoredEntityUUID(stack);
        return null;
    }
}
