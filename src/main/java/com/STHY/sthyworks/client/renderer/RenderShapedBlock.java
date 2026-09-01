package com.STHY.sthyworks.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import com.STHY.sthyworks.common.block.specialShapesBase.IShapedBlock;
import com.STHY.sthyworks.common.block.specialShapesBase.ShapedBlock;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderShapedBlock implements ISimpleBlockRenderingHandler {

    private final int renderId;

    public RenderShapedBlock(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        if (!(block instanceof IShapedBlock)) {
            return;
        }
        float[][] layers = ((IShapedBlock) block).getShapeLayers(null, 0, 0, 0, metadata);
        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        for (int i = 0; i < layers.length; i++) {
            float[] layer = layers[i];
            renderer.setRenderBounds(layer[0], layer[1], layer[2], layer[3], layer[4], layer[5]);
            for (int side = 0; side < 6; side++) {
                IIcon icon = ((IShapedBlock) block).getLayerIcon(null, 0, 0, 0, metadata, i, side);
                tessellator.startDrawingQuads();
                float shade = side == 0 ? 0.5F : (side == 1 ? 1.0F : (side <= 3 ? 0.8F : 0.6F));
                tessellator.setColorOpaque_F(shade, shade, shade);
                this.drawFace(block, side, renderer, tessellator, icon);
                tessellator.draw();
            }
        }
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (!(block instanceof IShapedBlock)) {
            return false;
        }
        float[][] layers = ((IShapedBlock) block).getShapeLayers(world, x, y, z, world.getBlockMetadata(x, y, z));
        boolean rendered = false;
        for (int i = 0; i < layers.length; i++) {
            float[] layer = layers[i];
            if (block instanceof ShapedBlock) {
                ((ShapedBlock) block).setRenderingContext(world, x, y, z, i);
            }
            renderer.setRenderBounds(layer[0], layer[1], layer[2], layer[3], layer[4], layer[5]);
            rendered |= renderer.renderStandardBlock(block, x, y, z);
        }
        if (block instanceof ShapedBlock) {
            ((ShapedBlock) block).resetRenderingContext();
        }
        return rendered;
    }

    private void drawFace(Block block, int side, RenderBlocks renderer, Tessellator tessellator, IIcon icon) {
        switch (side) {
            case 0:
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
                break;
            case 1:
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
                break;
            case 2:
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
                break;
            case 3:
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
                break;
            case 4:
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
                break;
            case 5:
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
                break;
        }
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return this.renderId;
    }
}
