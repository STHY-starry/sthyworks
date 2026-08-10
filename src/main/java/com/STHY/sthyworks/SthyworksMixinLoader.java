package com.STHY.sthyworks;

import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import com.STHY.sthyworks.mixins.Mixins;
import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;

@LateMixin
public class SthyworksMixinLoader implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.sthyworks.late.json";
    }

    @Nonnull
    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return IMixins.getLateMixins(Mixins.class, loadedMods);
    }
}
