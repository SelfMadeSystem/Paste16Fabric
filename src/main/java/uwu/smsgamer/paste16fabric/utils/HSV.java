package uwu.smsgamer.paste16fabric.utils;

import lombok.*;

import java.awt.*;
import java.util.Objects;

public class HSV extends Colour {
    @Getter
    @Setter
    public double h;       // angle in degrees between 0 and 360
    @Getter
    @Setter
    public double s;       // a fraction between 0 and 1
    @Getter
    @Setter
    public double v;       // a fraction between 0 and 1
    private RGB rgb;

    public HSV() {
    }

    public HSV(double h, double s, double v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }

    public HSV(double h, double s, double v, double a) {
        super(a);
        this.h = h;
        this.s = s;
        this.v = v;
    }

    @Override
    public double getRed() {
        if (rgb == null) rgb = Colour.hsv2rgb(this);
        return rgb.r;
    }

    @Override
    public double getGreen() {
        if (rgb == null) rgb = Colour.hsv2rgb(this);
        return rgb.g;
    }

    @Override
    public double getBlue() {
        if (rgb == null) rgb = Colour.hsv2rgb(this);
        return rgb.b;
    }

    @Override
    public double getHue() {
        return h;
    }

    @Override
    public double getSaturation() {
        return s;
    }

    @Override
    public double getValue() {
        return v;
    }

    public Color toColor() {
        return hsv2rgb(this).toColor();
    }

    @Override
    public HSV clone() {
        return new HSV(h, s, v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HSV hsv = (HSV) o;
        return MathUtils.approxEquals(hsv.h, h, 1) &&
          MathUtils.approxEquals(hsv.s, s, 0.002) &&
          MathUtils.approxEquals(hsv.v, v, 0.002);
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, s, v);
    }
}