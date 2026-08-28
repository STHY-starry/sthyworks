package com.STHY.sthyworks.common.event;

import net.minecraftforge.common.MinecraftForge;

public class EventLoader {

    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(new BlockHarvestDrops());
        MinecraftForge.EVENT_BUS.register(new LivingHurt());
        MinecraftForge.EVENT_BUS.register(new LivingAttack());
        MinecraftForge.EVENT_BUS.register(new FillBucket());
        MinecraftForge.EVENT_BUS.register(new PlayerWakeUp());
        MinecraftForge.EVENT_BUS.register(new EntityConstructing());
        MinecraftForge.EVENT_BUS.register(new EntityJoinWorld());
        MinecraftForge.EVENT_BUS.register(new LivingUpdate());
    }
}
