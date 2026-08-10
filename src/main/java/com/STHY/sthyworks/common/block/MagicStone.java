package com.STHY.sthyworks.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.entity.withoutEgg.Seat;

public class MagicStone extends Block {

    public MagicStone() {
        super(Material.rock);
        this.setBlockName("magicStone");
        this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.5F, 0.875F);
        this.setHardness(1.5F);
        this.setResistance(1.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockTextureName("sthyworks:magicStone");
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
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
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {

        if (worldIn.isRemote) return true;

        Seat seat = new Seat(worldIn);
        seat.setPosition(x + 0.5, y + 0.5, z + 0.5);
        seat.setBlockId(Block.getIdFromBlock(this));
        worldIn.spawnEntityInWorld(seat);
        player.mountEntity(seat);
        return true;
    }

    @Override
    public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta) {
        super.breakBlock(worldIn, x, y, z, blockBroken, meta);
        List<Seat> seats = worldIn
            .getEntitiesWithinAABB(Seat.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
        for (Seat seat : seats) {
            if (seat.riddenByEntity != null) {
                seat.riddenByEntity.mountEntity(null);
            }
            seat.setDead();
        }
    }

    @Override
    public boolean canSilkHarvest() {
        return true;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return null;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }
}
