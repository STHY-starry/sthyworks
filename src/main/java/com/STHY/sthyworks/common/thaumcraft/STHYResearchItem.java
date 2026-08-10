package com.STHY.sthyworks.common.thaumcraft;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;

public class STHYResearchItem extends ResearchItem {

    public STHYResearchItem(String key, String category) {
        super(key, category);
    }

    public STHYResearchItem(String key, String category, AspectList tags, int col, int row, int complex,
        ResourceLocation icon) {
        super(key, category, tags, col, row, complex, icon);
    }

    public STHYResearchItem(String key, String category, AspectList tags, int col, int row, int complex,
        ItemStack icon) {
        super(key, category, tags, col, row, complex, icon);
    }

    @Override
    public String getName() {
        return StatCollector.translateToLocal("sthyworks.research_name." + this.key);
    }

    @Override
    public String getText() {
        return StatCollector.translateToLocal("sthyworks.research_text." + this.key);
    }
}
