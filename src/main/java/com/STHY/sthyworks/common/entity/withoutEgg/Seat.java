package com.STHY.sthyworks.common.entity.withoutEgg;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.util.sthyUtils;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.entities.EntityAspectOrb;

public class Seat extends Entity {

    private int blockId;
    private int magicStoneId;

    public Seat(World world) {
        super(world);
        this.setSize(0.0F, 0.0F);
        this.preventEntitySpawning = true;
        this.magicStoneId = Block.getIdFromBlock(BlockLoader.magicStone);
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public int getMagicStoneId() {
        return magicStoneId;
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        blockId = tag.getInteger("blockId");
        magicStoneId = tag.getInteger("magicStoneId");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        tag.setInteger("blockId", blockId);
        tag.setInteger("magicStoneId", magicStoneId);
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (riddenByEntity == null || riddenByEntity.isDead) {
            setDead();
            return;
        }
        if (worldObj.isRemote) return;
        if (!(riddenByEntity instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) riddenByEntity;

        if (blockId == 0) return;
        if (blockId == magicStoneId) {
            if (sthyUtils.isAtSpecificTimes(worldObj, 10, 2)) {
                spawnRandomPrimalAspectOrb(
                    worldObj,
                    player.posX,
                    player.posY,
                    player.posZ,
                    1 + player.getRNG()
                        .nextInt(2));
            }
            // 还通过mixin提高了vis减免
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    private void spawnRandomPrimalAspectOrb(World world, double x, double y, double z, int aspectValue) {
        Aspect aspect;
        switch (world.rand.nextInt(6)) {
            case 0:
                aspect = Aspect.AIR;
                break;
            case 1:
                aspect = Aspect.EARTH;
                break;
            case 2:
                aspect = Aspect.FIRE;
                break;
            case 3:
                aspect = Aspect.WATER;
                break;
            case 4:
                aspect = Aspect.ORDER;
                break;
            case 5:
                aspect = Aspect.ENTROPY;
                break;
            default:
                aspect = Aspect.AIR;
        }
        EntityAspectOrb orb = new EntityAspectOrb(world, x, y, z, aspect, aspectValue);
        world.spawnEntityInWorld(orb);
    }
}
