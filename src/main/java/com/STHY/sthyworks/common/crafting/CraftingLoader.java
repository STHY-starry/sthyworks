package com.STHY.sthyworks.common.crafting;

public class CraftingLoader {

    public CraftingLoader() {
        Recipe.registerRecipe();
        Smelting.registerSmelting();
        Fuel.registerFuel();
        ArcaneRecipe.registerArcaneRecipe();
        Infusion.registerInfusionRecipe();
    }
}
