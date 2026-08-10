package com.STHY.sthyworks.common.potion;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.STHY.sthyworks.Config;
import com.STHY.sthyworks.common.attribute.STHYAttributes;
import com.STHY.sthyworks.common.damege.DamageLoader;

public class DeadlyPoison extends BasePotion {

    public DeadlyPoison() {
        super(Config.potionDeadlyPoison, false, 0x006400, "potion.deadlyPoison", 2, 5, false);
    }

    @Override
    public void performEffect(EntityLivingBase living, int amplifier) {
        double deadlyPoisonResistance = 0;
        if (living.getEntityAttribute(STHYAttributes.deadlyPoisonResistance) != null) {
            deadlyPoisonResistance = living.getEntityAttribute(STHYAttributes.deadlyPoisonResistance)
                .getAttributeValue();
        }
        if (deadlyPoisonResistance >= 1.0D) {
            living.removePotionEffect(PotionLoader.deadlyPoison.getId());
            return;
        } else if (deadlyPoisonResistance > living.getRNG()
            .nextDouble()) {
                living.removePotionEffect(PotionLoader.deadlyPoison.getId());
                return;
            }
        triggerEffect(living, amplifier);
    }

    public static void triggerEffect(EntityLivingBase living, int amplifier) {
        float damage = (float) Math.pow(1.1F, amplifier);
        int currentHurtResistantTime = living.hurtResistantTime;
        living.hurtResistantTime = 0;
        living.attackEntityFrom(DamageLoader.DeadlyPoison, damage);
        living.hurtResistantTime = currentHurtResistantTime;

        World world = living.worldObj;
        if (world.rand.nextInt(8) == 0) {
            int livingCurrentAmplifier = living.getActivePotionEffect(PotionLoader.deadlyPoison)
                .getAmplifier();
            int livingCurrentDuration = living.getActivePotionEffect(PotionLoader.deadlyPoison)
                .getDuration();
            if (livingCurrentAmplifier <= 0) return;
            living.removePotionEffect(PotionLoader.deadlyPoison.getId());
            living.addPotionEffect(
                new PotionEffect(
                    PotionLoader.deadlyPoison.getId(),
                    (int) (livingCurrentDuration * 0.75),
                    livingCurrentAmplifier - 1));

            List<EntityLivingBase> list = world
                .getEntitiesWithinAABB(EntityLivingBase.class, living.boundingBox.expand(2.0D, 1.0D, 2.0D));
            for (EntityLivingBase entity : list) {
                if (entity != living) {
                    if (entity.getActivePotionEffect(PotionLoader.deadlyPoison) == null) {
                        entity.addPotionEffect(
                            new PotionEffect(
                                PotionLoader.deadlyPoison.getId(),
                                (int) (livingCurrentDuration * 0.75),
                                livingCurrentAmplifier - 1));
                    }
                    int currentAmplifier = entity.getActivePotionEffect(PotionLoader.deadlyPoison)
                        .getAmplifier();
                    int currentDuration = entity.getActivePotionEffect(PotionLoader.deadlyPoison)
                        .getDuration();
                    entity.removePotionEffect(PotionLoader.deadlyPoison.getId());
                    entity.addPotionEffect(
                        new PotionEffect(
                            PotionLoader.deadlyPoison.getId(),
                            (int) (currentDuration * (world.rand.nextDouble() * 0.55D + 0.85D)),
                            currentAmplifier + world.rand.nextInt(2)));
                }
            }
        }
    }
}
