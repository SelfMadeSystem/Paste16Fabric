package uwu.smsgamer.paste.core.values;

import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste.core.config.ConfigManager;
import uwu.smsgamer.paste.core.module.PasteModule;
import uwu.smsgamer.paste.core.utils.MathUtils;

public class VNumber extends Val<Double> {
    protected double min;
    protected double max;
    protected double step;

    public VNumber(@NotNull PasteModule module, @NotNull String name, @NotNull Number defaultValue, Number min, Number max, Number step) {
        super(module, name, defaultValue.doubleValue());
        this.min = min.doubleValue();
        this.max = max.doubleValue();
        this.step = step.doubleValue();
    }

    public VNumber(@NotNull Val<?> parent, @NotNull String name, @NotNull Number defaultValue, Number min, Number max, Number step) {
        super(parent, name, defaultValue.doubleValue());
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
}
