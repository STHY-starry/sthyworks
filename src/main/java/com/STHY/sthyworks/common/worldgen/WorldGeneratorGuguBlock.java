package com.STHY.sthyworks.common.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGeneratorGuguBlock extends WorldGenerator {

    private Block blockToGenerate;
    private int minHeight;
    private int maxHeight;
    private int blocksPerChunk;
    private int maxClusterSize;

    public WorldGeneratorGuguBlock(Block block, int minHeight, int maxHeight, int blocksPerChunk, int maxClusterSize) {
        this.blockToGenerate = block;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.blocksPerChunk = blocksPerChunk;
        this.maxClusterSize = maxClusterSize;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        for (int i = 0; i < blocksPerChunk; i++) {
            int randX = x + rand.nextInt(16);
            int randY = rand.nextInt(this.maxHeight - this.minHeight) + this.minHeight;
            int randZ = z + rand.nextInt(16);

            if (world.getBlock(randX, randY, randZ)
                .isReplaceableOreGen(world, randX, randY, randZ, Blocks.stone)) {
                int clusterSize = rand.nextInt(this.maxClusterSize) + 1;

                for (int j = 0; j < clusterSize; ++j) {
                    int offsetX = rand.nextInt(3) - 1;
                    int offsetY = rand.nextInt(3) - 1;
                    int offsetZ = rand.nextInt(3) - 1;

                    int targetX = randX + offsetX;
                    int targetY = randY + offsetY;
                    int targetZ = randZ + offsetZ;

                    if (world.getBlock(targetX, targetY, targetZ)
                        .isReplaceableOreGen(world, targetX, targetY, targetZ, Blocks.stone)) {
                        world.setBlock(targetX, targetY, targetZ, this.blockToGenerate, 0, 2);
                    }
                }
            }
        }
        return true;
    }
}
