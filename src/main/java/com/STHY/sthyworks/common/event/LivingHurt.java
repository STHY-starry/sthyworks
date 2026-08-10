package com.STHY.sthyworks.common.event;

import net.minecraftforge.event.entity.living.LivingHurtEvent;

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
    }
}
