package com.STHY.sthyworks;

import com.STHY.sthyworks.client.event.ClientEventLoader;
import com.STHY.sthyworks.client.key.KeyLoader;
import com.STHY.sthyworks.client.renderer.RenderAdorableGugu;
import com.STHY.sthyworks.client.renderer.RenderGuguProjectile;
import com.STHY.sthyworks.common.entity.AdorableGugu;
import com.STHY.sthyworks.common.entity.GuguProjectile;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ClientProxy extends CommonProxy {

    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        registerEntityRenderers();
        new KeyLoader();
        new ClientEventLoader();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        super.serverStarting(event);
    }

    private void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(AdorableGugu.class, new RenderAdorableGugu());
        RenderingRegistry.registerEntityRenderingHandler(GuguProjectile.class, new RenderGuguProjectile());
    }

}
