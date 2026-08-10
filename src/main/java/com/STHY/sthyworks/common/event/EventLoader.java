package com.STHY.sthyworks.common.event;

import net.minecraftforge.common.MinecraftForge;

public class EventLoader {

    public EventLoader() {
        // FMLCommonHandler.instance().bus().register(new ItemPickup());
        MinecraftForge.EVENT_BUS.register(new PlayerInteract());
        MinecraftForge.EVENT_BUS.register(new BlockHarvestDrops());
        MinecraftForge.EVENT_BUS.register(new LivingHurt());
        MinecraftForge.EVENT_BUS.register(new LivingAttack());
        MinecraftForge.EVENT_BUS.register(new FillBucket());
        MinecraftForge.EVENT_BUS.register(new PlayerWakeUp());
        MinecraftForge.EVENT_BUS.register(new EntityConstructing());
    }
}
