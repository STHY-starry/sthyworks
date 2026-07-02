package com.STHY.sthyworks.common.event;

import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.STHY.sthyworks.common.potion.PotionLoader;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LivingHurt {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {

        // ReceivedDamageIncrease
        PotionEffect receivedDamageIncreaseEffect = event.entityLiving
            .getActivePotionEffect(PotionLoader.receivedDamageIncrease);
        if (receivedDamageIncreaseEffect != null) {
            event.ammount *= 1 + 0.1F * (receivedDamageIncreaseEffect.getAmplifier() + 1);
        }
    }
}
