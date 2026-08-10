package com.STHY.sthyworks.client.renderer;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.STHY.sthyworks.common.tileentity.TileEntityGuguAltar;

public class RenderTileEntityGuguAltar extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        if (!(tileEntity instanceof TileEntityGuguAltar)) {
            return;
        }
        TileEntityGuguAltar guguAltar = (TileEntityGuguAltar) tileEntity;
        ItemStack itemStack = guguAltar.getItemStack();

        if (itemStack == null || itemStack.getItem() == null) {
            return;
        }

        EntityItem entityItem = new EntityItem(guguAltar.getWorldObj(), 0, 0, 0, itemStack);
        entityItem.age = (int) guguAltar.getWorldObj()
            .getTotalWorldTime();
        entityItem.hoverStart = 0.0F;

        RenderManager.instance.renderEntityWithPosYaw(entityItem, x + 0.5, y + 0.875, z + 0.5, 0.0F, partialTicks);
    }
}
