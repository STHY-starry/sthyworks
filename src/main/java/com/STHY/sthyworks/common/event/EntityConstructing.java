package com.STHY.sthyworks.common.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

import com.STHY.sthyworks.common.attribute.STHYAttributes;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityConstructing {

    @SubscribeEvent
    public void onEntityJoinWorld(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) event.entity;
            if (entityLivingBase.getEntityAttribute(STHYAttributes.deadlyPoisonResistance) == null) {
                entityLivingBase.getAttributeMap()
                    .registerAttribute(STHYAttributes.deadlyPoisonResistance);
            }

            if (event.entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entity;
                if (player.getEntityAttribute(STHYAttributes.pathologyExpertise) == null) {
                    player.getAttributeMap()
                        .registerAttribute(STHYAttributes.pathologyExpertise);
                }
            }
        }
    }
}
