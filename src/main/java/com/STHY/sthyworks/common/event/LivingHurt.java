package com.STHY.sthyworks.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.STHY.sthyworks.api.IItemShield;
import com.STHY.sthyworks.common.potion.PotionLoader;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LivingHurt {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {

        // ReceivedDamageIncrease
        if (event.entityLiving.isPotionActive(PotionLoader.receivedDamageIncrease)) {
            event.ammount *= 1 + 0.1F * (event.entityLiving.getActivePotionEffect(PotionLoader.receivedDamageIncrease)
                .getAmplifier() + 1);
        }

        // 盾类武器效果
        if (event.entityLiving instanceof EntityPlayer && !event.source.isUnblockable()) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            if (player.getHeldItem() != null && player.getHeldItem()
                .getItem() instanceof IItemShield) {
                IItemShield item = (IItemShield) player.getHeldItem()
                    .getItem();
                item.onOwnerHurt(player.getHeldItem(), player, event);
            }
        }
    }
}
