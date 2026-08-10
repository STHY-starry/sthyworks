package com.STHY.sthyworks.common.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.entity.AdorableGugu;

public class TileEntityGuguAltar extends TileEntity {

    private ItemStack itemStack;
    private int progress;

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey("ItemStack")) {
            itemStack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("ItemStack"));
        } else {
            itemStack = null;
        }
        progress = tag.getInteger("Progress");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (itemStack != null) {
            NBTTagCompound itemTag = new NBTTagCompound();
            this.itemStack.writeToNBT(itemTag);
            tag.setTag("ItemStack", itemTag);
        }
        tag.setInteger("Progress", progress);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj.isRemote) {
            return;
        }
        if (itemStack == null || itemStack.getItem() == null) {
            progress = 0;
            return;
        }
        Item item = itemStack.getItem();
        if (item == Item.getItemFromBlock(BlockLoader.guguBlock) && canWorkGuguBlock()) {
            progress++;
            if (progress >= 500) {
                AdorableGugu gugu = new AdorableGugu(worldObj);
                gugu.setLocationAndAngles(xCoord + 0.5, yCoord + 1.5, zCoord + 0.5, 0, 0);
                worldObj.spawnEntityInWorld(gugu);
                itemStack = null;
                progress = 0;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    }

    private boolean canWorkGuguBlock() {
        long time = worldObj.getWorldTime() % 24000;
        if (time < 17000 || time > 19000) {
            return false;
        }

        if (worldObj.isRaining() || worldObj.isThundering()) {
            return false;
        }
        return true;
    }
}
