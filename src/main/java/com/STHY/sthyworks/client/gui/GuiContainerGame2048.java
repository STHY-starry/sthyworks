package com.STHY.sthyworks.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.STHY.sthyworks.common.inventory.ContainerGame2048;
import com.STHY.sthyworks.common.tileentity.TileEntityGame2048;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiContainerGame2048 extends GuiContainer {

    public TileEntityGame2048 tileEntityGame2048;

    private static final int BUTTON_START = 0;
    private static final int BUTTON_END = 1;
    String title = StatCollector.translateToLocal("guiContainerGame2048.title");
    String scoreBoard = StatCollector.translateToLocal("guiContainerGame2048.scoreBoard");
    String start = StatCollector.translateToLocal("guiContainerGame2048.start");
    String end = StatCollector.translateToLocal("guiContainerGame2048.end");

    public GuiContainerGame2048(ContainerGame2048 container) {
        super(container);
        this.xSize = 176;
        this.ySize = 166;
        this.tileEntityGame2048 = container.tileEntityGame2048;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        this.mc.getTextureManager()
            .bindTexture(new ResourceLocation("sthyworks", "textures/gui/Game2048.png"));
        int offsetX = (this.width - this.xSize) / 2;
        int offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj
            .drawString(title, (this.xSize - this.fontRendererObj.getStringWidth(title)) / 2, 13, 0x404040);
        this.fontRendererObj
            .drawString(scoreBoard, 103 + (52 - this.fontRendererObj.getStringWidth(scoreBoard)) / 2, 33, 0x404040);
        this.fontRendererObj.drawString(
            tileEntityGame2048.score + "",
            103 + (52 - this.fontRendererObj.getStringWidth(tileEntityGame2048.score + "")) / 2,
            54,
            0x404040);
    }

    @Override
    public void initGui() {
        super.initGui();
        int offsetX = (this.width - this.xSize) / 2;
        int offsetY = (this.height - this.ySize) / 2;
        if (tileEntityGame2048.isStart) {
            this.buttonList.add(new GuiButton(BUTTON_END, offsetX + 104, offsetY + 77, 52, 14, end));
        } else {
            this.buttonList.add(new GuiButton(BUTTON_START, offsetX + 104, offsetY + 77, 52, 14, start));
        }
    }
}
