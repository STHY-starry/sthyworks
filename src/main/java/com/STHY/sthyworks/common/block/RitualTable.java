package com.STHY.sthyworks.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.block.specialShapesBase.BlockShapes;
import com.STHY.sthyworks.common.block.specialShapesBase.ShapedBlock;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class RitualTable extends ShapedBlock implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    private IIcon ordoAndPerditioIcon;
    @SideOnly(Side.CLIENT)
    private IIcon opSideIcon;
    @SideOnly(Side.CLIENT)
    private IIcon[] pillarIcons;

    public RitualTable() {
        super(Material.rock, BlockShapes.RITUAL_TABLE);
        this.setBlockName("ritualTable");
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        super.registerBlockIcons(reg);
        this.blockIcon = reg.registerIcon("sthyworks:ritual_table_base");
        this.ordoAndPerditioIcon = reg.registerIcon("sthyworks:ritual_table_op");
        this.opSideIcon = reg.registerIcon("sthyworks:ritual_table_opSide");
        this.pillarIcons = new IIcon[] {
            reg.registerIcon("sthyworks:ritualTable_pillar_aer"),
            reg.registerIcon("sthyworks:ritualTable_pillar_terra"),
            reg.registerIcon("sthyworks:ritualTable_pillar_ignis"),
            reg.registerIcon("sthyworks:ritualTable_pillar_aqua")
        };
    }

    @Override
    public IIcon getLayerIcon(IBlockAccess world, int x, int y, int z, int meta, int layerIndex, int side) {
        if (layerIndex >= 2 && layerIndex <= 5) {
            return this.pillarIcons[layerIndex - 2];
        }
        if (layerIndex == 1) {
            if (side == 1){
                return this.ordoAndPerditioIcon;
            }else if (side != 0){
                return this.opSideIcon;
            }
        }
        return this.blockIcon;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
