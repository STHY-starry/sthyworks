package com.STHY.sthyworks;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraftforge.client.MinecraftForgeClient;

import com.STHY.sthyworks.client.event.ClientEventLoader;
import com.STHY.sthyworks.client.key.KeyLoader;
import com.STHY.sthyworks.client.renderer.RenderAdorableGugu;
import com.STHY.sthyworks.client.renderer.RenderDemonThornProjectile;
import com.STHY.sthyworks.client.renderer.RenderPathogenesisProjectile;
import com.STHY.sthyworks.client.renderer.RenderStrawMan;
import com.STHY.sthyworks.client.renderer.RenderTileEntityGuguAltar;
import com.STHY.sthyworks.common.entity.AdorableGugu;
import com.STHY.sthyworks.common.entity.StrawMan;
import com.STHY.sthyworks.common.entity.withoutEgg.DemonThornProjectile;
import com.STHY.sthyworks.common.entity.withoutEgg.GuguProjectile;
import com.STHY.sthyworks.common.entity.withoutEgg.PathogenesisProjectile;
import com.STHY.sthyworks.common.item.ItemLoader;
import com.STHY.sthyworks.common.tileentity.TileEntityGuguAltar;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fox.spiteful.avaritia.render.CosmicItemRenderer;

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
        registerTileEntityRenderers();
        registerItemRenderers();
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
        RenderingRegistry.registerEntityRenderingHandler(StrawMan.class, new RenderStrawMan());

        RenderingRegistry.registerEntityRenderingHandler(GuguProjectile.class, new RenderSnowball(Items.apple, 0));
        RenderingRegistry.registerEntityRenderingHandler(DemonThornProjectile.class, new RenderDemonThornProjectile());
        RenderingRegistry
            .registerEntityRenderingHandler(PathogenesisProjectile.class, new RenderPathogenesisProjectile());
    }

    private void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGuguAltar.class, new RenderTileEntityGuguAltar());
    }

    private void registerItemRenderers() {
        MinecraftForgeClient.registerItemRenderer(ItemLoader.starrySky, new CosmicItemRenderer());
    }
}
