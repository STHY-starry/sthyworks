package com.STHY.sthyworks.common.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.STHY.sthyworks.common.util.ObsessionManager;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityJoinWorld {

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!event.world.isRemote && event.entity instanceof EntityPlayerMP) {
            ObsessionManager.syncObsessionToPlayer((EntityPlayerMP) event.entity);
        }
    }
}
