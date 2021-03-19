package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.util.math.*;

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

    public static float getAngleDifference(float a, float b) {
        return ((((a - b) % 360F) + 540F) % 360F) - 180F;
    }

    public static double roundInc(double val, double inc) {
        if (inc == 0) return val;
        val = val - inc / 2;
        return Math.round(val * (1d / inc)) / (1d / inc);
    }

    public static double clamp(double a, double min, double max) {
        return Math.max(Math.min(a, max), min);
    }

    public static Vec3d rotToVec(double yaw, double pitch) {
        double xzLen = cos_dd(pitch);
        return new Vec3d(xzLen * cos_dd(yaw), sin_dd(pitch), xzLen * sin_dd(-yaw));
    }

    //Thx https://github.com/detel/bezier-curve/blob/master/bezeir-curves.cpp
    public static int factorial(int n) {
        if (n <= 1) return 1;
        else n *= factorial(n - 1);
        return n;
    }

    public static float binomialCoff(float n, float k) {
        float ans;
        ans = factorial((int) n) / (float) (factorial((int) k) * factorial((int) (n - k)));
        return ans;
    }

    public static Vec3f getBezier(Vec3f[] pts, double t) {
        Vec3f v = new Vec3f();
        v.x = (float) (Math.pow((1 - t), 3) * pts[0].x + 3 * t * Math.pow((1 - t), 2) * pts[1].x + 3 * (1 - t) * Math.pow(t, 2) * pts[2].x + Math.pow(t, 3) * pts[3].x);
        v.y = (float) (Math.pow((1 - t), 3) * pts[0].y + 3 * t * Math.pow((1 - t), 2) * pts[1].y + 3 * (1 - t) * Math.pow(t, 2) * pts[2].y + Math.pow(t, 3) * pts[3].y);

        return v;
    }

    public static Vec3f getBezierGeneralized(Vec3f[] pts, double t) {
        Vec3f v = new Vec3f();
        v.x = 0;
        v.y = 0;
        int clicks = pts.length;
        for (int i = 0; i < clicks; i++) {
            double pow = Math.pow((1 - t), (clicks - 1 - i));
            v.x = (float) (v.x + binomialCoff((float) (clicks - 1), (float) i) * Math.pow(t, i) * pow * pts[i].x);
            v.y = (float) (v.y + binomialCoff((float) (clicks - 1), (float) i) * Math.pow(t, i) * pow * pts[i].y);
        }
        return v;
    }

    public static Vec3f[] getBezierPoints(Vec3f[] pts) {
        Vec3f[] result = new Vec3f[50];
        for (int i = 0; i < 50; i++) result[i] = getBezierGeneralized(pts, i / 50F);
        return result;
    }
}
