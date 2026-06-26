package com.STHY.sthyworks.common.event;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.fluid.FluidLoader;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FillBucket {
    @SubscribeEvent
    public void onFillBucket(FillBucketEvent event) {
        int x = event.target.blockX;
        int y = event.target.blockY;
        int z = event.target.blockZ;
        Block block = event.world.getBlock(x, y, z);
        int meta = event.world.getBlockMetadata(x, y, z);
        if (block == BlockLoader.fluidMagic && meta == 0){
            FluidStack fluidStack = new FluidStack(FluidLoader.magic, 1000);
            event.world.setBlock(x, y, z, Blocks.air);
            event.result = FluidContainerRegistry.fillFluidContainer(fluidStack, event.current);
            event.setResult(Event.Result.ALLOW);
        }
    }
}
