package com.STHY.sthyworks.common.worldgen;

import net.minecraftforge.common.MinecraftForge;

public class WorldGeneratorLoader {
    public WorldGeneratorLoader(){
        MinecraftForge.ORE_GEN_BUS.register(new WorldGeneratorHandler());
    }
}
