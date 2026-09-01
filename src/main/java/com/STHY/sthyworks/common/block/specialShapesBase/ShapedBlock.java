package com.STHY.sthyworks.common.block.specialShapesBase;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public abstract class ShapedBlock extends Block implements IShapedBlock {

    public static int renderId = -1;

    private final float[][] shapeLayers;

    private IBlockAccess renderingWorld;
    private int renderingX;
    private int renderingY;
    private int renderingZ;
    private int renderingLayerIndex = -1;

    protected ShapedBlock(Material material, float[][] shapeLayers) {
        super(material);
        this.shapeLayers = shapeLayers;
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setBoundsFromShape();
    }

    public void setRenderingContext(IBlockAccess world, int x, int y, int z, int layerIndex) {
        this.renderingWorld = world;
        this.renderingX = x;
        this.renderingY = y;
        this.renderingZ = z;
        this.renderingLayerIndex = layerIndex;
    }

    public void resetRenderingContext() {
        this.renderingWorld = null;
        this.renderingX = 0;
        this.renderingY = 0;
        this.renderingZ = 0;
        this.renderingLayerIndex = -1;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.getLayerIcon(
            this.renderingWorld,
            this.renderingX,
            this.renderingY,
            this.renderingZ,
            meta,
            this.renderingLayerIndex,
            side);
    }

    @Override
    public IIcon getLayerIcon(IBlockAccess world, int x, int y, int z, int meta, int layerIndex, int side) {
        return this.blockIcon;
    }

    @Override
    public float[][] getShapeLayers(IBlockAccess world, int x, int y, int z, int meta) {
        return this.shapeLayers;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return renderId;
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask,
        List<AxisAlignedBB> list, Entity collider) {
        for (float[] layer : this.shapeLayers) {
            this.setBlockBounds(layer[0], layer[1], layer[2], layer[3], layer[4], layer[5]);
            super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
        }
        this.setBoundsFromShape();
    }

    private void setBoundsFromShape() {
        float minX = 1.0F, minY = 1.0F, minZ = 1.0F;
        float maxX = 0.0F, maxY = 0.0F, maxZ = 0.0F;
        for (float[] layer : this.shapeLayers) {
            minX = Math.min(minX, layer[0]);
            minY = Math.min(minY, layer[1]);
            minZ = Math.min(minZ, layer[2]);
            maxX = Math.max(maxX, layer[3]);
            maxY = Math.max(maxY, layer[4]);
            maxZ = Math.max(maxZ, layer[5]);
        }
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }
}
