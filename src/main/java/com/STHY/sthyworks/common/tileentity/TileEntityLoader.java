package com.STHY.sthyworks.common.tileentity;

import net.minecraft.tileentity.TileEntity;

import com.STHY.sthyworks.sthyworks;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityLoader {

    public TileEntityLoader() {
        registerTileEntity(TileEntityGuguAltar.class, "guguAltar");
        registerTileEntity(TileEntityVoidGate.class, "voidGate");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
        GameRegistry.registerTileEntity(tileEntityClass, sthyworks.MODID + ":" + id);
    }
}
