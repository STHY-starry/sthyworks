package com.STHY.sthyworks.common.worldgen;

import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;

import com.STHY.sthyworks.common.block.BlockLoader;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldGeneratorHandler {

    private int x;
    private int z;

    private WorldGenerator guguBlockGenerator = new WorldGeneratorGuguBlock(BlockLoader.guguBlock, 0, 128, 4, 4);

    @SubscribeEvent
    public void onOreGenerate(OreGenEvent.Post event) {
        if (event.worldX != x || event.worldZ != z) {
            x = event.worldX;
            z = event.worldZ;
            guguBlockGenerator.generate(event.world, event.rand, event.worldX, 0, event.worldZ);
        }
    }
}
