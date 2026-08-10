package com.STHY.sthyworks.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import com.STHY.sthyworks.common.tileentity.TileEntityGame2048;

public class ContainerGame2048 extends Container {

    public TileEntityGame2048 tileEntityGame2048;

    public ContainerGame2048(TileEntityGame2048 tileEntityGame2048) {
        super();

        this.tileEntityGame2048 = tileEntityGame2048;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.addSlotToContainer(new SlotGame2048(tileEntityGame2048, 4 * i + j, 23 + i * 18, 34 + j * 18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        return null;
    }
}
