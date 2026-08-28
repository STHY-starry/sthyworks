package com.STHY.sthyworks.common.attribute;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class STHYAttributes {

    public static final IAttribute controlOfMagic = createRangedAttribute(
        "sthyworks.controlOfMagic",
        "Control of Magic",
        1.0D,
        1.0D,
        4096.0D);

    private static IAttribute createRangedAttribute(String unlocalizedName, String description, double defaultValue,
        double minValue, double maxValue) {
        return (new RangedAttribute(unlocalizedName, defaultValue, minValue, maxValue)).setDescription(description)
            .setShouldWatch(true);
    }
}
