package com.STHY.sthyworks.common.thaumcraft;

import net.minecraft.util.ResourceLocation;

import thaumcraft.api.aspects.Aspect;

public class AspectLoader {

    public static Aspect LOVE;
    public static Aspect OBSESSION;
    public static Aspect ABYSS;

    public AspectLoader() {
        registerAspects();
    }

    private static void registerAspects() {
        LOVE = new Aspect(
            "amor",
            0xFF5A5A,
            new Aspect[] { Aspect.HEAL, Aspect.SENSES },
            new ResourceLocation("sthyworks", "textures/aspects/amor.png"),
            1);
        OBSESSION = new Aspect(
            "obsessus",
            0x6E008A,
            new Aspect[] { AspectLoader.LOVE, Aspect.MAGIC },
            new ResourceLocation("sthyworks", "textures/aspects/obsessus.png"),
            1);
        ABYSS = new Aspect(
            "abyssus",
            0x1015A9,
            new Aspect[] { Aspect.DARKNESS, Aspect.EARTH },
            new ResourceLocation("sthyworks", "textures/aspects/abyssus.png"),
            1);
    }
}
