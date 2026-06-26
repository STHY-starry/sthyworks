package com.STHY.sthyworks.common.item;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.fluid.FluidLoader;

public class BucketMagic extends ItemBucket {

    public BucketMagic() {
        super(BlockLoader.fluidMagic);
        this.setContainerItem(Items.bucket);
        this.setUnlocalizedName("bucketMagic");
        this.setTextureName("sthyworks:bucketMagic");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        FluidContainerRegistry
            .registerFluidContainer(FluidLoader.magic, new ItemStack(this), FluidContainerRegistry.EMPTY_BUCKET);
    }
}
