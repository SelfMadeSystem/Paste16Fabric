package uwu.smsgamer.paste.core.utils;

import uwu.smsgamer.paste.core.utils.interfaces.IRender2D;

import java.awt.*;

public class Render2D {
    public static IRender2D util;

    /**
     * Draws a full circle.
     *
     * @param x x position
     * @param y y position
     * @param r radius
     * @param color color
     */
    public static void drawCircle(float x, float y, float r, Color color) {
        drawHollowCircle(x, y, r, 0, color);
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
    public static void drawHollowCircle(float x, float y, float r, float ir, Color color) {
        drawHollowCircleSegm(x, y, r, ir, 0, 360, 0, 360, color);
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
    public static void drawCircleSegm(float x, float y, float r, float startDeg, float endDeg, Color color) {
        drawHollowCircleSegm(x, y, r, 0, startDeg, endDeg, startDeg, endDeg, color);

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
    public static void drawHollowCircleSegm(
      float x, float y, float r, float ir,
      float innerStartDeg, float innerEndDeg,
      float outerStartDeg, float outerEndDeg,
      Color color) {
        util.enableBlend();
        util.disableTexture2D();
        util.blendFuncSeparate(770, 771, 1, 0);
        color(color);
        util.startQuads();
        final float ol = outerEndDeg - outerStartDeg;
        final float oa = outerStartDeg;
        final float il = innerEndDeg - innerStartDeg;
        final float ia = innerStartDeg;
        for (int i = 0; i <= 356; i += 4) {
            float f = i/360f;
            float oSin = MathUtils.sin_fd(f * ol + oa) * r;
            float oCos = MathUtils.cos_fd(f * ol + oa) * r;
            float iSin = MathUtils.sin_fd(f * il + ia) * ir;
            float iCos = MathUtils.cos_fd(f * il + ia) * ir;
            f = (i+4)/360f;
            float oSin1 = MathUtils.sin_fd(f * ol + oa) * r;
            float oCos1 = MathUtils.cos_fd(f * ol + oa) * r;
            float iSin1 = MathUtils.sin_fd(f * il + ia) * ir;
            float iCos1 = MathUtils.cos_fd(f * il + ia) * ir;
            util.vertex(x + iSin, y - iCos);
            util.vertex(x + oSin, y - oCos);
            util.vertex(x + oSin1, y - oCos1);
            util.vertex(x + iSin1, y - iCos1);
        }
        util.end();
        util.enableTexture2D();
        util.disableBlend();
    }

    public static void color(Color c) {
        util.color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

    public static void color(int r, int g, int b) {
        util.color(r, g, b, 255);
    }
}
