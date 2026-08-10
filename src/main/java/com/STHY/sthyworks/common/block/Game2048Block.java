package com.STHY.sthyworks.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.inventory.GuiElementLoader;
import com.STHY.sthyworks.common.tileentity.TileEntityGame2048;
import com.STHY.sthyworks.sthyworks;

public class Game2048Block extends Block implements ITileEntityProvider {

    public Game2048Block() {
        super(Material.rock);
        this.setBlockName("game2048Block");
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockTextureName("sthyworks:game2048Block");
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        player.openGui(sthyworks.instance, GuiElementLoader.GUI_ID_GAME2048, worldIn, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityGame2048();
    }
}
