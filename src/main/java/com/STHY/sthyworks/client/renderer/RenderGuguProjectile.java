package com.STHY.sthyworks.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;

@SideOnly(Side.CLIENT)
public class RenderGuguProjectile extends RenderSnowball {
    public RenderGuguProjectile() {
        super(Items.apple, 0);
    }
}
