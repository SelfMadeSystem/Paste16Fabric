package uwu.smsgamer.paste16fabric.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.util.math.*;

public class Render3D implements MinecraftHelper {
    public static void drawBox(Matrix4f matrix, Box box, Colour colour) {
        box = box.offset(mc.gameRenderer.getCamera().getPos().multiply(-1));
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        drawBox(matrix, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, (float) colour.getRed(), (float) colour.getGreen(), (float) colour.getBlue(), (float) colour.getAlpha());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.popMatrix();
    }

    private static void drawBox(Matrix4f matrix, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha) {
        float f = (float)x1;
        float g = (float)y1;
        float h = (float)z1;
        float i = (float)x2;
        float j = (float)y2;
        float k = (float)z2;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(5, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, f, g, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, g, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, g, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, g, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, j, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, j, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, j, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, g, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, j, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, g, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, g, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, g, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, j, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, j, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, j, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, g, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, j, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, g, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, g, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, g, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, g, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, g, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, g, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, j, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, j, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, f, j, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, j, h).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, j, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, j, k).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, i, j, k).color(red, green, blue, alpha).next();
        tessellator.draw();
    }
}
