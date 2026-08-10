package com.STHY.sthyworks.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import com.STHY.sthyworks.common.item.ItemLoader;

public class TileEntityGame2048 extends TileEntity implements IInventory {

    private ItemStack[][] itemStacks = new ItemStack[4][4];
    public boolean isStart = false;
    public int score = 0;

    @Override
    public int getSizeInventory() {
        return 16;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        return itemStacks[slotIn / 4][slotIn % 4];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        itemStacks[index / 4][index % 4] = stack;
    }

    @Override
    public String getInventoryName() {
        return "";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
            : player
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D)
                <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        isStart = compound.getBoolean("isStart");
        score = compound.getInteger("score");

        for (int i = 0; i < itemStacks.length; i++) {
            for (int j = 0; j < itemStacks[i].length; j++) {
                itemStacks[i][j] = new ItemStack(ItemLoader.item2048, 1, compound.getInteger(i + " " + j));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setBoolean("isStart", isStart);
        compound.setInteger("score", score);

        for (int i = 0; i < itemStacks.length; i++) {
            for (int j = 0; j < itemStacks[i].length; j++) {
                if (itemStacks[i][j] != null && itemStacks[i][j].getItem() != null) {
                    compound.setInteger(i + " " + j, itemStacks[i][j].getItemDamage());
                } else {
                    compound.setInteger(i + " " + j, 0);
                }
            }
        }
    }
}
