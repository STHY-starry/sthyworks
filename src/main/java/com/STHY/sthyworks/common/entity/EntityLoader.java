package com.STHY.sthyworks.common.entity;

import net.minecraft.entity.Entity;

import com.STHY.sthyworks.common.entity.withoutEgg.DemonThornProjectile;
import com.STHY.sthyworks.common.entity.withoutEgg.GuguProjectile;
import com.STHY.sthyworks.common.entity.withoutEgg.PathogenesisProjectile;
import com.STHY.sthyworks.common.entity.withoutEgg.Seat;
import com.STHY.sthyworks.sthyworks;

import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityLoader {

    private static int nextID = 0;

    public EntityLoader() {
        registerEntity(AdorableGugu.class, "AdorableGugu", 64, 3, true, 0xFFC0CB, 0xFFFFE0);
        registerEntity(StrawMan.class, "StrawMan", 64, 3, true, 0xEFFF00, 0x00FF22);

        registerWithoutEgg(GuguProjectile.class, "GuguProjectile", 64, 1, true);
        registerWithoutEgg(DemonThornProjectile.class, "DemonThornProjectile", 64, 1, true);
        registerWithoutEgg(PathogenesisProjectile.class, "PathogenesisProjectile", 64, 1, true);
        registerWithoutEgg(Seat.class, "Seat", 64, 1, false);
    }

    private void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange,
        int updateFrequency, boolean sendsVelocityUpdates, int backgroundEggColour, int foregroundEggColour) {
        EntityRegistry.registerGlobalEntityID(
            entityClass,
            entityName,
            EntityRegistry.findGlobalUniqueEntityId(),
            backgroundEggColour,
            foregroundEggColour);
        EntityRegistry.registerModEntity(
            entityClass,
            entityName,
            nextID++,
            sthyworks.instance,
            trackingRange,
            updateFrequency,
            sendsVelocityUpdates);
    }

    private void registerWithoutEgg(Class<? extends Entity> entityClass, String entityName, int trackingRange,
        int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(
            entityClass,
            entityName,
            nextID++,
            sthyworks.instance,
            trackingRange,
            updateFrequency,
            sendsVelocityUpdates);
    }
}
