package com.STHY.sthyworks.common.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasePotion extends Potion {

    private static final ResourceLocation texture = new ResourceLocation("sthyworks", "textures/gui/potion.png");
    private final int tickrate;
    private final boolean halveTickrateWithAmplifier;

    public BasePotion(int id, boolean isBad, int color, String name, int iconIndex, int tickrate,
        boolean halveTickrateWithAmplifier) {
        super(id, isBad, color);
        this.setPotionName(name);
        this.tickrate = tickrate;
        this.setIconIndex(iconIndex % 8, iconIndex / 8);
        this.halveTickrateWithAmplifier = halveTickrateWithAmplifier;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(texture);
        return super.getStatusIconIndex();
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        if (tickrate < 0) return false;
        int k = halveTickrateWithAmplifier ? (tickrate >> amplifier) : tickrate;
        return k > 1 ? duration % k == 0 : true;
    }

    @Override
    public void performEffect(EntityLivingBase living, int amplifier) {

    }
}
