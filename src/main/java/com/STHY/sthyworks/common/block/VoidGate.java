package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.tileentity.TileEntityVoidGate;
import com.cleanroommc.modularui.factory.GuiFactories;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VoidGate extends BlockDirectional implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    IIcon faceIcon;
    @SideOnly(Side.CLIENT)
    IIcon verticalIcon;

    public VoidGate() {
        super(Material.rock);
        this.setBlockName("voidGate");
        this.setHardness(3.0F);
        this.setResistance(8.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockTextureName("sthyworks:voidGate");
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        faceIcon = reg.registerIcon("sthyworks:voidGate_face");
        verticalIcon = reg.registerIcon("sthyworks:voidGate_vertical");
        blockIcon = reg.registerIcon("sthyworks:voidGate_default");
    }

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        int l = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        worldIn.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 0 || side == 1) return this.verticalIcon;
        if (side == getFrontSide(meta)) return this.faceIcon;
        return this.blockIcon;
    }

    public static int getFrontSide(int meta) {
        switch (meta & 3) {
            case 2:
                return 2;
            case 3:
                return 5;
            case 0:
                return 3;
            case 1:
                return 4;
            default:
                return 0;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityVoidGate();
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX,
        float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            GuiFactories.tileEntity()
                .open(playerIn, x, y, z);
        }
        return true;
    }

    public static boolean isTopSidePowered(World worldIn, int x, int y, int z) {
        if (worldIn.getIndirectPowerOutput(x, y + 1, z, 1)) {
            return true;
        }
        return worldIn.getBlock(x, y + 1, z) instanceof BlockRedstoneWire && worldIn.getBlockMetadata(x, y + 1, z) > 0;
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess worldIn, int x, int y, int z, int side) {
        TileEntity tileEntity = worldIn.getTileEntity(x, y, z);
        if (!(tileEntity instanceof TileEntityVoidGate)) return 0;
        TileEntityVoidGate gate = (TileEntityVoidGate) tileEntity;
        if (side == 1) {
            return gate.getWorking() ? 15 : 0;
        } else if (side != ForgeDirection.OPPOSITES[getFrontSide(worldIn.getBlockMetadata(x, y, z))]) return 0;
        return gate.getCompletionTimer() > 0 ? 15 : 0;
    }
}
