package com.STHY.sthyworks.common.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import com.STHY.sthyworks.common.damege.DamageLoader;
import com.STHY.sthyworks.common.potion.PotionLoader;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LivingAttack {

    private static final String EXTRA_SoulAnnihilationDAMAGE_TAG = "sthyworks_ExtraSoulAnnihilationDamage_applied";

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {

        // Soul Annihilation deals additional damage
        EntityLivingBase victim = event.entityLiving;
        Entity attackerEntity = event.source.getSourceOfDamage();

        if (attackerEntity instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) attackerEntity;

            PotionEffect potionEffect = attacker.getActivePotionEffect(PotionLoader.soulAnnihilation);
            if (potionEffect != null) {

                if (victim.getEntityData()
                    .getBoolean(EXTRA_SoulAnnihilationDAMAGE_TAG)) {
                    return;
                }

                float extraDamage = (potionEffect.getAmplifier() + 1) * 2;
                EntityDamageSource entityDamageSource = new EntityDamageSource(
                    DamageLoader.Soul.getDamageType(),
                    attacker);
                victim.getEntityData()
                    .setBoolean(EXTRA_SoulAnnihilationDAMAGE_TAG, true);
                victim.attackEntityFrom(entityDamageSource, extraDamage);
                victim.getEntityData()
                    .setBoolean(EXTRA_SoulAnnihilationDAMAGE_TAG, false);
            }
        }
    }
}
