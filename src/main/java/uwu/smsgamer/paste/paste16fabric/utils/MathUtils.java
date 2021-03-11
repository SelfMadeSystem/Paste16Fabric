package uwu.smsgamer.paste.core.utils;

import uwu.smsgamer.paste.core.utils.interfaces.IMathUtils;

public class MathUtils {
    public static IMathUtils utils;

    public static boolean approxEquals(double x, double y, double diff) {
        return Math.abs(x - y) <= diff;
    }

    public static double sin_dr(double r) {
        return utils.sin(r);
    }

    public static float sin_fr(float r) {
        return (float) utils.sin(r);
    }

    public static double sin_dd(double r) {
        return utils.sin(Math.toRadians(r));
    }

    public static float sin_fd(float r) {
        return (float) utils.sin(Math.toRadians(r));
    }

    public static double cos_dr(double r) {
        return utils.cos(r);
    }

    public static float cos_fr(float r) {
        return (float) utils.cos(r);
    }

    public static double cos_dd(double r) {
        return utils.cos(Math.toRadians(r));
    }

    public static float cos_fd(float r) {
        return (float) utils.cos(Math.toRadians(r));
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
