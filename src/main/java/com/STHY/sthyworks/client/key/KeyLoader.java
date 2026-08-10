package com.STHY.sthyworks.client.key;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class KeyLoader {

    public KeyLoader() {
        ClientRegistry.registerKeyBinding(toggleTooltipsDisplay);
    }

    public static KeyBinding toggleTooltipsDisplay = new KeyBinding(
        "key.sthyworks.toggleTooltipsDisplay",
        Keyboard.KEY_LSHIFT,
        "key.categories.sthyworks");
}
