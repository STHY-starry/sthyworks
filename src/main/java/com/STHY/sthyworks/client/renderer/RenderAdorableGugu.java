package com.STHY.sthyworks.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderAdorableGugu extends RenderBiped {

    private static final ResourceLocation texture = new ResourceLocation("sthyworks:textures/models/entity/adorableGugu.png");

    public RenderAdorableGugu() {
        super(new ModelBiped(0.0F, 0.0F, 64, 64), 0.5F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}
