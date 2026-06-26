package com.STHY.sthyworks.common.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ChatComponentTranslation;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class ItemPickup {

    @SubscribeEvent
    public void onPlayerItemPickup(PlayerEvent.ItemPickupEvent event) {
        EntityItem item = event.pickedUp;
        String itemName = item.getEntityItem()
            .getDisplayName();

        event.player.addChatMessage(new ChatComponentTranslation("event.pickup.message", itemName));
    }
}
