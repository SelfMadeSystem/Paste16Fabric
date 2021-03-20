package uwu.smsgamer.paste16fabric.values;

import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste16fabric.config.ConfigManager;
import uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors.*;
import uwu.smsgamer.paste16fabric.module.PasteModule;
import uwu.smsgamer.paste16fabric.utils.MathUtils;

public class VNumber extends Val<Number> {
    protected double min;
    protected double max;
    protected double step;

    public VNumber(@NotNull PasteModule module, @NotNull String name, @NotNull Number defaultValue, Number min, Number max, Number step, String description) {
        super(module, name, defaultValue.doubleValue(), description);
        this.min = min.doubleValue();
        this.max = max.doubleValue();
        this.step = step.doubleValue();
    }

    public VNumber(@NotNull Val<?> parent, @NotNull String name, @NotNull Number defaultValue, Number min, Number max, Number step, String description) {
        super(parent, name, defaultValue.doubleValue(), description);
        this.min = min.doubleValue();
        this.max = max.doubleValue();
        this.step = step.doubleValue();
    }

    @Override
    public void setValue(Object o) {
        if (o instanceof Number) setValue((Number) o);
        else if (o instanceof String) setValue(Double.parseDouble(o.toString()));
        else throw new IllegalArgumentException(o.getClass().getName() + " not of type Number or String!");
        ConfigManager.getInstance().setValue(this, true);
    }

    public void setValue(Number n) {
        this.value = MathUtils.clamp(MathUtils.roundInc(n.doubleValue(), step), min, max);
        ConfigManager.getInstance().setValue(this, true);
    }

    public byte getByte() {
        return getValue().byteValue();
    }

    public short getShort() {
        return getValue().shortValue();
    }

    public int getInt() {
        return getValue().intValue();
    }

    public long getLong() {
        return getValue().longValue();
    }

    public float getFloat() {
        return getValue().floatValue();
    }

    public double getDouble() {
        return getValue().doubleValue();
    }

    public double getScaledValue() {
        return (getDouble() - this.min) / (this.max - this.min);
    }

    public void setScaledValue(double d) {
        setValue(d * (this.max - this.min) + this.min + this.step / 2);
    }

    @Override
    public AbstractValueEditor<VNumber, Number> getValueEditor() {
        return new NumberEditor(this);
    }

    public static class Percent extends VNumber {
        public Percent(@NotNull PasteModule module, @NotNull String name, @NotNull Number defaultValue, Number min, Number max, Number step, String description) {
            super(module, name, defaultValue, min, max, step, description);
        }

        public Percent(@NotNull Val<?> parent, @NotNull String name, @NotNull Number defaultValue, Number min, Number max, Number step, String description) {
            super(parent, name, defaultValue, min, max, step, description);
        }

        @Override
        public String getStringValue() {
            return String.format("%d%%", (int) (getDouble() * 100));
        }
    }

    public static class Int extends VNumber {
        public Int(@NotNull PasteModule module, @NotNull String name, @NotNull Number defaultValue, Number min, Number max, Number step, String description) {
            super(module, name, defaultValue, min, max, step, description);
        }

        public Int(@NotNull Val<?> parent, @NotNull String name, @NotNull Number defaultValue, Number min, Number max, Number step, String description) {
            super(parent, name, defaultValue, min, max, step, description);
        }

        @Override
        public String getStringValue() {
            return String.format("%d", (int) getDouble());
        }
    }
}
