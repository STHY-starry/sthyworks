package com.STHY.sthyworks.client.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.STHY.sthyworks.common.entity.withoutEgg.DemonThornProjectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDemonThornProjectile extends Render {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
        "sthyworks:textures/models/entity/demonThornProjectile.png");

    // 局部偏移（单位：世界格），正值含义：
    // LOCAL_OFFSET_X : 向前（锥头方向）
    // LOCAL_OFFSET_Y : 向上
    // LOCAL_OFFSET_Z : 向右（从尾部看向头部时的右侧）
    private static final float LOCAL_OFFSET_X = -0.3F;
    private static final float LOCAL_OFFSET_Y = 0.15F;
    private static final float LOCAL_OFFSET_Z = -0.15F;

    @Override
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.doRender((DemonThornProjectile) entity, x, y, z, p_76986_8_, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

    public void doRender(DemonThornProjectile entity, double x, double y, double z, float p_76986_8_,
        float partialTicks) {
        this.bindEntityTexture(entity);

        GL11.glPushMatrix();
        // 平移到实体世界坐标
        GL11.glTranslatef((float) x, (float) y, (float) z);

        // 朝向旋转
        float yaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;
        float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        GL11.glRotatef(yaw - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(pitch, 0.0F, 0.0F, 1.0F);

        // 模型缩放与锚点
        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        float scale = 0.045F;
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);

        // ---- 应用局部偏移（除以缩放系数以保证世界格单位） ----
        GL11.glTranslatef(LOCAL_OFFSET_X / scale, LOCAL_OFFSET_Y / scale, LOCAL_OFFSET_Z / scale);

        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        final int segments = 4;
        final float length = 16.0F;
        final float radius = 2.0F;
        final float coneLength = 4.0F;

        final float baseX = 0.0F; // 尾部
        final float bodyEndX = length; // 头部（锥头底部）
        final float tipX = length + coneLength; // 锥尖

        // 纹理坐标
        final float barWidth = 0.2F;
        final float coneUmin = 0.8F, coneUmax = 1.0F;
        final float coneVmin = 0.0F, coneVmax = 0.25F;

        tessellator.startDrawing(GL11.GL_TRIANGLES);

        // 预计算四个截面顶点
        float[] xVert = new float[segments];
        float[] zVert = new float[segments];
        for (int i = 0; i < segments; i++) {
            float angle = (float) (i * Math.PI * 2.0 / segments);
            xVert[i] = (float) Math.cos(angle) * radius;
            zVert[i] = (float) Math.sin(angle) * radius;
        }

        // ---- 绘制四个侧面 ----
        for (int i = 0; i < segments; i++) {
            int iNext = (i + 1) % segments;
            float x1 = xVert[i], z1 = zVert[i];
            float x2 = xVert[iNext], z2 = zVert[iNext];

            float midAngle = (float) ((i + 0.5) * Math.PI * 2.0 / segments);
            float normY = (float) Math.cos(midAngle);
            float normZ = (float) Math.sin(midAngle);
            tessellator.setNormal(0.0F, normY, normZ);

            float uMin = i * barWidth;
            float uMax = (i + 1) * barWidth;
            float vHead = 0.0F; // 对应纹理顶部
            float vTail = 1.0F; // 对应纹理底部

            // 主体侧面
            tessellator.addVertexWithUV(baseX, x1, z1, uMin, vTail);
            tessellator.addVertexWithUV(baseX, x2, z2, uMax, vTail);
            tessellator.addVertexWithUV(bodyEndX, x2, z2, uMax, vHead);
            tessellator.addVertexWithUV(baseX, x1, z1, uMin, vTail);
            tessellator.addVertexWithUV(bodyEndX, x2, z2, uMax, vHead);
            tessellator.addVertexWithUV(bodyEndX, x1, z1, uMin, vHead);

            // 锥头侧面
            tessellator.setNormal(0.0F, normY, normZ);
            tessellator.addVertexWithUV(tipX, 0.0F, 0.0F, coneUmin, coneVmin);
            tessellator.addVertexWithUV(bodyEndX, x1, z1, coneUmax, coneVmax);
            tessellator.addVertexWithUV(bodyEndX, x2, z2, coneUmax, coneVmax);
        }

        // ---- 尾部端面（使用锥头纹理区域） ----
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        float eUmin = 0.8F, eUmax = 1.0F;
        float eVmin = 0.25F, eVmax = 0.5F;
        // 从外部（-X）看逆时针顺序
        tessellator.addVertexWithUV(baseX, xVert[0], zVert[0], eUmin, eVmin);
        tessellator.addVertexWithUV(baseX, xVert[3], zVert[3], eUmax, eVmin);
        tessellator.addVertexWithUV(baseX, xVert[2], zVert[2], eUmax, eVmax);
        tessellator.addVertexWithUV(baseX, xVert[0], zVert[0], eUmin, eVmin);
        tessellator.addVertexWithUV(baseX, xVert[2], zVert[2], eUmax, eVmax);
        tessellator.addVertexWithUV(baseX, xVert[1], zVert[1], eUmin, eVmax);

        tessellator.draw();

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
}
