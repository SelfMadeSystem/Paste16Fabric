package uwu.smsgamer.paste.paste16fabric.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

import java.awt.*;

public class Render2D {
    public static final int
      GL_POINTS = 0x0,
      GL_LINES = 0x1,
      GL_LINE_LOOP = 0x2,
      GL_LINE_STRIP = 0x3,
      GL_TRIANGLES = 0x4,
      GL_TRIANGLE_STRIP = 0x5,
      GL_TRIANGLE_FAN = 0x6,
      GL_QUADS = 0x7;
    public static float Z = -1.43F;

    /**
     * Draws a full circle.
     *
     * @param x x position
     * @param y y position
     * @param r radius
     * @param color color
     */
    public static void drawCircle(MatrixStack matrixStack, float x, float y, float r, Color color) {
        drawHollowCircle(matrixStack, x, y, r, 0, color);
    }

    /**
     * Draws a circle with a hole.
     *
     * @param x x position
     * @param y y position
     * @param r radius
     * @param ir inner radius
     * @param color color
     */
    public static void drawHollowCircle(MatrixStack matrixStack, float x, float y, float r, float ir, Color color) {
        drawHollowCircleSegm(matrixStack, x, y, r, ir, 0, 360, 0, 360, color);
    }

    /**
     * Draws a circle segment.
     *
     * @param x x position
     * @param y y position
     * @param r radius
     * @param startDeg Starting degrees
     * @param endDeg Ending degrees
     * @param color color
     */
    public static void drawCircleSegm(MatrixStack matrixStack, float x, float y, float r, float startDeg, float endDeg, Color color) {
        drawHollowCircleSegm(matrixStack, x, y, r, 0, startDeg, endDeg, startDeg, endDeg, color);

    }

    /**
     * Draws a circle segment.
     *
     * @param x x position
     * @param y y position
     * @param r radius
     * @param ir inner radius
     * @param innerStartDeg Start degrees for inner
     * @param innerEndDeg End degrees for inner
     * @param outerStartDeg Start degrees for outer
     * @param outerEndDeg End degrees for outer
     * @param color color
     */
    public static void drawHollowCircleSegm(MatrixStack matrixStack,
                                            float x, float y, float r, float ir,
                                            float innerStartDeg, float innerEndDeg,
                                            float outerStartDeg, float outerEndDeg,
                                            Color color) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        final float ol = outerEndDeg - outerStartDeg;
        final float oa = outerStartDeg;
        final float il = innerEndDeg - innerStartDeg;
        final float ia = innerStartDeg;
        float red = color.getRed() / 255f;
        float green = color.getGreen() / 255f;
        float blue = color.getBlue() / 255F;
        float alpha = color.getAlpha() / 255F;
        for (int i = 0; i <= 356; i += 4) {
            float f = i / 360f;
            float oSin = MathUtils.sin_fd(f * ol + oa) * r;
            float oCos = MathUtils.cos_fd(f * ol + oa) * r;
            float iSin = MathUtils.sin_fd(f * il + ia) * ir;
            float iCos = MathUtils.cos_fd(f * il + ia) * ir;
            f = (i + 4) / 360f;
            float oSin1 = MathUtils.sin_fd(f * ol + oa) * r;
            float oCos1 = MathUtils.cos_fd(f * ol + oa) * r;
            float iSin1 = MathUtils.sin_fd(f * il + ia) * ir;
            float iCos1 = MathUtils.cos_fd(f * il + ia) * ir;
            matrixStack.push();
            Matrix4f matrix4f = matrixStack.peek().getModel();
            bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
            bufferBuilder.vertex(matrix4f, x + iSin, y - iCos, Z).color(red, green, blue, alpha).next();
            bufferBuilder.vertex(matrix4f, x + oSin, y - oCos, Z).color(red, green, blue, alpha).next();
            bufferBuilder.vertex(matrix4f, x + oSin1, y - oCos1, Z).color(red, green, blue, alpha).next();
            bufferBuilder.vertex(matrix4f, x + iSin1, y - iCos1, Z).color(red, green, blue, alpha).next();
            bufferBuilder.end();
            BufferRenderer.draw(bufferBuilder);
            matrixStack.pop();
        }

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(515);
    }

    public static void drawRect(MatrixStack matrixStack, float x, float y, float width, float height, Color color) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        float red = color.getRed() / 255f;
        float green = color.getGreen() / 255f;
        float blue = color.getBlue() / 255F;
        float alpha = color.getAlpha() / 255F;

        matrixStack.push();
        Matrix4f matrix4f = matrixStack.peek().getModel();
        bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, x, y, Z).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix4f, x + width, y, Z).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix4f, x + width, y + height, Z).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix4f, x, y + height, Z).color(red, green, blue, alpha).next();
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        matrixStack.pop();

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(515);
    }
}
