package uwu.smsgamer.paste.paste16fabric.utils;

import lombok.*;

import java.awt.*;
import java.util.Objects;

public class RGB extends Colour {
    @Getter
    @Setter
    public double r;       // a fraction between 0 and 1
    @Getter
    @Setter
    public double g;       // a fraction between 0 and 1
    @Getter
    @Setter
    public double b;       // a fraction between 0 and 1
    private HSV hsv;

    public RGB() {
    }

    public RGB(Color c) {
        this(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, c.getAlpha() / 255f);
    }

    public RGB(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public RGB(double r, double g, double b, double a) {
        super(a);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public double getRed() {
        return r;
    }

    @Override
    public double getGreen() {
        return g;
    }

    @Override
    public double getBlue() {
        return b;
    }

    @Override
    public double getHue() {
        return 0;
    }

    @Override
    public double getSaturation() {
        return 0;
    }

    @Override
    public double getValue() {
        return 0;
    }

    public Color toColor() {
        try {
            return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + "; " + r + " " + g + " " + b);
        }
    }

    @Override
    public RGB clone() {
        return new RGB(r, g, b, a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGB rgb = (RGB) o;
        return MathUtils.approxEquals(rgb.r, r, 0.002) &&
          MathUtils.approxEquals(rgb.g, g, 0.002) &&
          MathUtils.approxEquals(rgb.b, b, 0.002);
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }
}