package com.STHY.sthyworks.common.attribute;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class STHYAttributes {

    public static final IAttribute pathologyExpertise = createRangedAttribute(
        "sthyworks.pathologyExpertise",
        "Pathology Expertise",
        1.0D,
        1.0D,
        4096.0D);
    public static final IAttribute deadlyPoisonResistance = createRangedAttribute(
        "sthyworks.deadlyPoisonResistance",
        "Deadly Poison Resistance",
        0.0D,
        0.0D,
        1.0D);

    private static IAttribute createRangedAttribute(String unlocalizedName, String description, double defaultValue,
        double minValue, double maxValue) {
        return (new RangedAttribute(unlocalizedName, defaultValue, minValue, maxValue)).setDescription(description)
            .setShouldWatch(true);
    }
}
