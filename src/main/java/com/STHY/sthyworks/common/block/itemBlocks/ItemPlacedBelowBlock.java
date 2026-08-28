package com.STHY.sthyworks.common.block.itemBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPlacedBelowBlock extends ItemBlock {

    public ItemPlacedBelowBlock(Block block) {
        super(block);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        y--;
        if (stack.stackSize == 0) {
            return false;
        } else if (!player.canPlayerEdit(x, y, z, side, stack)) {
            return false;
        } else if (y < 0) {
            return false;
        } else if (!world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, player, stack)) {
            return false;
        }
        int meta = this.getMetadata(stack.getItemDamage());
        int placeMeta = this.field_150939_a.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);

        if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, placeMeta)) {
            world.playSoundEffect(
                (double) ((float) x + 0.5F),
                (double) ((float) y + 0.5F),
                (double) ((float) z + 0.5F),
                this.field_150939_a.stepSound.func_150496_b(),
                (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
                this.field_150939_a.stepSound.getPitch() * 0.8F);
            --stack.stackSize;
        }
        return true;
    }

    @Override
    public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack stack) {
        return y > 0 && world.canPlaceEntityOnSide(this.field_150939_a, x, y - 1, z, false, side, (Entity) null, stack);
    }
}
