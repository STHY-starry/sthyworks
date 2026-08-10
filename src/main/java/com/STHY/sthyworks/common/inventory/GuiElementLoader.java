package com.STHY.sthyworks.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.STHY.sthyworks.client.gui.GuiContainerGame2048;
import com.STHY.sthyworks.common.tileentity.TileEntityGame2048;
import com.STHY.sthyworks.sthyworks;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiElementLoader implements IGuiHandler {

    public static final int GUI_ID_GAME2048 = 1;

    public GuiElementLoader() {
        NetworkRegistry.INSTANCE.registerGuiHandler(sthyworks.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // InventoryPlayer invPlayer = player.inventory;
        switch (ID) {
            case GUI_ID_GAME2048:
                return new ContainerGame2048((TileEntityGame2048) world.getTileEntity(x, y, z));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // InventoryPlayer invPlayer = player.inventory;
        switch (ID) {
            case GUI_ID_GAME2048:
                return new GuiContainerGame2048(
                    new ContainerGame2048((TileEntityGame2048) world.getTileEntity(x, y, z)));
            default:
                return null;
        }
    }
}
