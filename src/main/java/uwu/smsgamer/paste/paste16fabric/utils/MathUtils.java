package uwu.smsgamer.paste.paste16fabric.utils;

import net.minecraft.util.math.MathHelper;

public class MathUtils {
    public static boolean approxEquals(double x, double y, double diff) {
        return Math.abs(x - y) <= diff;
    }

    public static double sin_dr(double r) {
        return sin(r).doubleValue();
    }

    public static float sin_fr(float r) {
        return sin(r).floatValue();
    }

    public static double sin_dd(double r) {
        return sin(Math.toRadians(r)).doubleValue();
    }

    public static float sin_fd(float r) {
        return sin(Math.toRadians(r)).floatValue();
    }

    public static double cos_dr(double r) {
        return cos(r).doubleValue();
    }

    public static float cos_fr(float r) {
        return cos(r).floatValue();
    }

    public static double cos_dd(double r) {
        return cos(Math.toRadians(r)).doubleValue();
    }

    public static float cos_fd(float r) {
        return cos(Math.toRadians(r)).floatValue();
    }

    public static Number sin(Number r) {
        return MathHelper.sin(r.floatValue());
    }

    public static Number cos(Number r) {
        return MathHelper.cos(r.floatValue());
    }

    public static float wrapAngle180(float f) {
        f %= 360.0F;
        if (f >= 180.0F) {
            f -= 360.0F;
        }
        if (f < -180.0F) {
            f += 360.0F;
        }
        return f;
    }

    public static double roundInc(double val, double inc) {
        if (inc == 0) return val;
        val = val - inc / 2;
        return Math.round(val * (1d / inc)) / (1d / inc);
    }

    public static float getAngleDifference(float a, float b) {
        return ((((a - b) % 360F) + 540F) % 360F) - 180F;
    }

    public static double clamp(double a, double min, double max) {
        return Math.max(Math.min(a, max), min);
    }
}
