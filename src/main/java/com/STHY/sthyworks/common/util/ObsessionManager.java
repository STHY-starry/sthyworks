package com.STHY.sthyworks.common.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import com.STHY.sthyworks.common.network.ClientCacheData;
import com.STHY.sthyworks.common.network.NetworkLoader;
import com.STHY.sthyworks.common.network.PacketRequestObsession;
import com.STHY.sthyworks.common.network.PacketSyncObsession;

public class ObsessionManager {

    private static final String TAG_OBSESSION = "sthyworks:Obsession";

    public static NBTTagCompound getPlayerObsessionNBT(EntityPlayer player) {
        NBTTagCompound entityData = player.getEntityData();
        NBTTagCompound persisted;
        if (!entityData.hasKey(EntityPlayer.PERSISTED_NBT_TAG, 10)) {
            entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
        }
        persisted = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        if (!persisted.hasKey(TAG_OBSESSION, 10)) {
            persisted.setTag(TAG_OBSESSION, new NBTTagCompound());
        }
        return persisted.getCompoundTag(TAG_OBSESSION);
    }

    public static void addPlayerObsession(EntityPlayer player, int amount) {
        NBTTagCompound obsessionNBT = getPlayerObsessionNBT(player);
        int newAmount = obsessionNBT.getInteger("obsession") + amount;
        if (newAmount < 0) {
            newAmount = 0;
        } else if (newAmount > 100) {
            newAmount = 100;
        }
        obsessionNBT.setInteger("obsession", newAmount);
        if (!player.worldObj.isRemote) {
            syncObsessionToPlayer((EntityPlayerMP) player);
        }
    }

    public static void setPlayerObsession(EntityPlayer player, int amount) {
        NBTTagCompound obsessionNBT = getPlayerObsessionNBT(player);
        if (amount < 0) {
            amount = 0;
        } else if (amount > 100) {
            amount = 100;
        }
        obsessionNBT.setInteger("obsession", amount);
        if (!player.worldObj.isRemote) {
            syncObsessionToPlayer((EntityPlayerMP) player);
        }
    }

    public static int getPlayerObsession(EntityPlayer player) {
        NBTTagCompound obsessionNBT = getPlayerObsessionNBT(player);
        return obsessionNBT.getInteger("obsession");
    }

    public static void syncObsessionToPlayer(EntityPlayerMP player) {
        NetworkLoader.INSTANCE.sendTo(new PacketSyncObsession(getPlayerObsession(player)), player);
    }

    public static void requestObsessionFromServer() {
        NetworkLoader.INSTANCE.sendToServer(new PacketRequestObsession());
    }

    public static float calculateVisDiscount(EntityPlayer player) {
        int obsession = player.worldObj.isRemote ? ClientCacheData.obsession : getPlayerObsession(player);
        float obsessionDiscount;
        if (obsession <= 40) {
            obsessionDiscount = 25.0F - (float) Math.pow(obsession - 40, 2) / 64.0F;
        } else {
            obsessionDiscount = 25.0F - (float) Math.pow(obsession - 40, 2) / 100.0F;
        }
        return Math.round(obsessionDiscount * 100.0F) / 100.0F;
    }
}
