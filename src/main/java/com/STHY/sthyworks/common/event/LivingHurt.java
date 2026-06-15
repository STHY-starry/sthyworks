package com.STHY.sthyworks.common.event;

import com.STHY.sthyworks.common.potion.PotionLoader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LivingHurt {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {

        // Fall Protection
        if (event.source.getDamageType().equals("fall")) {
            PotionEffect potionEffect = event.entityLiving.getActivePotionEffect(PotionLoader.fallProtection);
            if (potionEffect != null) {
                event.ammount /= potionEffect.getAmplifier() + 2;
            }
        }
    }
}
