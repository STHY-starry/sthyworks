package com.STHY.sthyworks.common.block.specialShapesBase;

import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public interface IShapedBlock {

    float[][] getShapeLayers(IBlockAccess world, int x, int y, int z, int meta);

    IIcon getLayerIcon(IBlockAccess world, int x, int y, int z, int meta, int layerIndex, int side);
}
