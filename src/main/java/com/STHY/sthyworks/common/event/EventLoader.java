package com.STHY.sthyworks.common.event;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLCommonHandler;

public class EventLoader {

    public EventLoader() {
        FMLCommonHandler.instance()
            .bus()
            .register(new ItemPickup());
        MinecraftForge.EVENT_BUS.register(new PlayerRightClickBlock());
        MinecraftForge.EVENT_BUS.register(new BlockHarvestDrops());
        MinecraftForge.EVENT_BUS.register(new LivingHurt());
        MinecraftForge.EVENT_BUS.register(new LivingAttack());
    }
}
