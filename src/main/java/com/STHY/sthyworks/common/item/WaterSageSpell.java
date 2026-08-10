package com.STHY.sthyworks.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class WaterSageSpell extends Item {

    public WaterSageSpell() {
        setUnlocalizedName("waterSageSpell");
        setTextureName("sthyworks:waterSageSpell");
        setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {

        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(worldIn, player, true);
        if (mop == null) {
            return itemStackIn;
        }
        if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int x = mop.blockX;
            int y = mop.blockY;
            int z = mop.blockZ;

            if (!player.canPlayerEdit(x, y, z, mop.sideHit, itemStackIn)) return itemStackIn;

            Block block = worldIn.getBlock(x, y, z);
            int meta = worldIn.getBlockMetadata(x, y, z);
            if (block == Blocks.flowing_water && meta != 0) {
                worldIn.setBlock(x, y, z, Blocks.flowing_water);
            } else {
                switch (mop.sideHit) {
                    case 0:
                        y--;
                        break;
                    case 1:
                        y++;
                        break;
                    case 2:
                        z--;
                        break;
                    case 3:
                        z++;
                        break;
                    case 4:
                        x--;
                        break;
                    case 5:
                        x++;
                        break;
                    default:
                        return itemStackIn;
                }
                worldIn.setBlock(x, y, z, Blocks.flowing_water);
            }
            return itemStackIn;
        }
        return itemStackIn;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.block;
    }
}
