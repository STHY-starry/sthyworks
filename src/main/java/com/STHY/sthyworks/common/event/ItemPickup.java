package com.STHY.sthyworks.common.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ChatComponentText;

import com.STHY.sthyworks.sthyworks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class ItemPickup {

    @SubscribeEvent
    public void onPlayerItemPickup(PlayerEvent.ItemPickupEvent event) {
        EntityItem item = event.pickedUp;
        String itemName = item.getEntityItem()
            .getDisplayName();
        int itemCount = item.getEntityItem().stackSize;

        String message = "§e你拾取了 §r" + itemName + "§e!";
        event.player.addChatMessage(new ChatComponentText(message));
        sthyworks.LOG.info(message);
    }
}
