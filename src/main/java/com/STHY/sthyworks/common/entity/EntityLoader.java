package com.STHY.sthyworks.common.entity;

import com.STHY.sthyworks.sthyworks;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;

public class EntityLoader {
    private static int nextID = 0;

    public EntityLoader() {
        registerEntity(AdorableGugu.class, "AdorableGugu", 64, 3, true,0xFFC0CB,0xFFFFE0);
        registerProjectile(GuguProjectile.class, "GuguProjectile", 64, 10, true);
    }

    private void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange,
                                int updateFrequency, boolean sendsVelocityUpdates,int backgroundEggColour, int foregroundEggColour) {
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, EntityRegistry.findGlobalUniqueEntityId(),
            backgroundEggColour, foregroundEggColour);
        EntityRegistry.registerModEntity(entityClass, entityName, nextID++,
            sthyworks.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    private void registerProjectile(Class<? extends Entity> projectileClass, String projectileName,
                                    int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(projectileClass, projectileName, nextID++,
            sthyworks.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }
}

