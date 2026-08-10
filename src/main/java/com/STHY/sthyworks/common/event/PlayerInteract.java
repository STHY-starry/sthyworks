package com.STHY.sthyworks.common.event;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.STHY.sthyworks.common.block.BlockLoader;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerInteract {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (event.entityPlayer.worldObj.isRemote) {
            return;
        }
        Block clickedBlock = event.world.getBlock(event.x, event.y, event.z);

        if (clickedBlock == BlockLoader.pigBlock) {
            interactPigBlock(event);
        }
    }

    public void interactPigBlock(PlayerInteractEvent event) {
        event.setCanceled(true);

        String message = "§d你右键点击了猪猪方块！§6哼唧~";
        event.entityPlayer.addChatMessage(new ChatComponentText(message));

        ItemStack porkchop = new ItemStack(Items.porkchop, 1);

        EntityItem entityItem = new EntityItem(
            event.world,
            event.entityPlayer.posX,
            event.entityPlayer.posY + 0.5,
            event.entityPlayer.posZ,
            porkchop);
        entityItem.motionY = 0.2;
        event.world.spawnEntityInWorld(entityItem);

        event.entityPlayer.addChatMessage(new ChatComponentText("§a生猪肉已掉落！"));

        event.world.playSoundAtEntity(event.entityPlayer, "mob.pig.say", 1.0F, 1.0F);

        Entity tnt = new EntityTNTPrimed(event.world, event.x + 0.5, event.y + 0.5, event.z + 0.5, event.entityPlayer);
        event.world.spawnEntityInWorld(tnt);
    }
}
