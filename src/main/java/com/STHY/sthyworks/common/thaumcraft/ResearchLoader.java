package com.STHY.sthyworks.common.thaumcraft;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.crafting.ArcaneRecipe;
import com.STHY.sthyworks.common.crafting.Infusion;
import com.STHY.sthyworks.common.item.ItemLoader;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;

public class ResearchLoader {

    public ResearchLoader() {
        ResearchCategories.registerCategory(
            "STHYWORKS",
            new ResourceLocation("sthyworks", "textures/items/InstructionManual.png"),
            new ResourceLocation("sthyworks", "textures/misc/background.png"));

        registerResearch();
    }

    private static void registerResearch() {
        new STHYResearchItem(
            "GUGU_ALTAR",
            "STHYWORKS",
            new AspectList().add(Aspect.MAGIC, 1)
                .add(AspectLoader.LOVE, 1),
            0,
            1,
            1,
            new ItemStack(BlockLoader.guguAltar))
                .setPages(
                    new ResearchPage("sthyworks.research_page.GUGU_ALTAR.1"),
                    new ResearchPage(ArcaneRecipe.arcaneGuguAltar))
                .registerResearchItem();

        new STHYResearchItem(
            "STARRY_SKY",
            "STHYWORKS",
            new AspectList().add(Aspect.DARKNESS, 1)
                .add(Aspect.LIGHT, 1),
            -2,
            -1,
            1,
            new ItemStack(ItemLoader.starrySky))
                .setPages(
                    new ResearchPage("sthyworks.research_page.STARRY_SKY.1"),
                    new ResearchPage(Infusion.infusionStarrySky))
                .setParents("INFUSION", "GUGU_ALTAR")
                .setConcealed()
                .registerResearchItem();
        ThaumcraftApi.addWarpToResearch("STARRY_SKY", 2);

        new STHYResearchItem(
            "NEW_ASPECT",
            "STHYWORKS",
            new AspectList(),
            2,
            0,
            0,
            new ResourceLocation("thaumcraft", "textures/misc/r_aspects.png"))
                .setPages(
                    new ResearchPage("sthyworks.research_page.NEW_ASPECT.1"),
                    new ResearchPage(
                        new AspectList().add(AspectLoader.LOVE, 1)
                            .add(AspectLoader.OBSESSION, 1)))
                .setAutoUnlock()
                .setRound()
                .registerResearchItem();
    }
}
