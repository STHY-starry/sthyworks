package com.STHY.sthyworks.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

import com.STHY.sthyworks.common.item.VenerableShadow;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LivingUpdate {

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!(event.entityLiving instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) event.entityLiving;
        if (player.getCurrentArmor(2) == null || !(player.getCurrentArmor(2)
            .getItem() instanceof VenerableShadow)) {
            if (player.getEntityData()
                .getBoolean("VenerableArmorEquipped")) {
                player.getEntityData()
                    .removeTag("VenerableArmorEquipped");
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                player.sendPlayerAbilities();
            }
        }
    }
}
