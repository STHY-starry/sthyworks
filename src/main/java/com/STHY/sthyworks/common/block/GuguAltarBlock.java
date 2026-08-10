package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.tileentity.TileEntityGuguAltar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuguAltarBlock extends Block implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;

    public GuguAltarBlock() {
        super(Material.rock);
        this.setBlockName("guguAltar");
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.75F, 0.9375F);
        this.setHardness(2.0F);
        this.setResistance(15.0F);
        this.setBlockTextureName("sthyworks:guguAltar");
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        topIcon = reg.registerIcon("sthyworks:guguAltar_top");
        sideIcon = reg.registerIcon("sthyworks:guguAltar_side");
        bottomIcon = reg.registerIcon("sthyworks:guguAltar_bottom");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return topIcon;
        } else if (side == 0) {
            return bottomIcon;
        } else {
            return sideIcon;
        }
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
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityGuguAltar();
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        TileEntity tileEntity = worldIn.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityGuguAltar) {
            TileEntityGuguAltar guguAltar = (TileEntityGuguAltar) tileEntity;
            if (guguAltar.getItemStack() != null) {
                if (player.getHeldItem() == null) {
                    player.setCurrentItemOrArmor(0, guguAltar.getItemStack());
                    guguAltar.setItemStack(null);
                    return true;
                }
            } else {
                if (player.getHeldItem() != null) {
                    ItemStack heldItem = player.getHeldItem();
                    ItemStack singleStack = heldItem.splitStack(1);
                    guguAltar.setItemStack(singleStack);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta) {
        TileEntity tileEntity = worldIn.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityGuguAltar) {
            TileEntityGuguAltar guguAltar = (TileEntityGuguAltar) tileEntity;
            ItemStack itemStack = guguAltar.getItemStack();
            if (itemStack != null && itemStack.getItem() != null) {
                EntityItem entityItem = new EntityItem(worldIn, x + 0.5, y + 0.5, z + 0.5, itemStack);
                worldIn.spawnEntityInWorld(entityItem);
            }
        }
        super.breakBlock(worldIn, x, y, z, blockBroken, meta);
    }
}
