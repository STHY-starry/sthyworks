package com.STHY.sthyworks.client.key;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyLoader {

    public KeyLoader() {
        ClientRegistry.registerKeyBinding(showTime);
    }

    public static KeyBinding showTime = new KeyBinding("key.sthyworks.showTime", Keyboard.KEY_H, "key.categories.sthyworks");

}
