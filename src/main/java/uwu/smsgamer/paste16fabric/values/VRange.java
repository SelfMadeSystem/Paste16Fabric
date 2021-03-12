package uwu.smsgamer.paste16fabric.values;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste16fabric.config.ConfigManager;
import uwu.smsgamer.paste16fabric.module.PasteModule;
import uwu.smsgamer.paste16fabric.utils.MathUtils;

import java.util.Map;

@Getter
public class VRange extends Val<VRange.Range> {
    protected final double min;
    protected final double max;
    protected final double step;

    public VRange(@NotNull PasteModule module, @NotNull String name, @NotNull Number defaultMin, @NotNull Number defaultMax, Number min, Number max, Number step) {
        super(module, name, new Range(defaultMin, defaultMax));
        this.min = min.doubleValue();
        this.max = max.doubleValue();
        this.step = step.doubleValue();
    }

    public VRange(@NotNull Val<?> parent, @NotNull String name, @NotNull Number defaultMin, @NotNull Number defaultMax, Number min, Number max, Number step) {
        super(parent, name, new Range(defaultMin, defaultMax));
        this.min = min.doubleValue();
        this.max = max.doubleValue();
        this.step = step.doubleValue();
    }

    @Override
    public void setValue(Object o) {
        if (o instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) o;
            if (map.containsKey("min")) getValue().min = (((Number) map.get("min")).doubleValue());
            if (map.containsKey("max")) getValue().min = (((Number) map.get("max")).doubleValue());
        } else if (o instanceof Range) {
            this.value = (Range) o;
        } else if (o instanceof String) {
            String[] split = ((String) o).split(":");
            if (split.length != 2) throw new IllegalStateException(o + " is not of format 'min:max'!");
            try {
                double min = Double.parseDouble(split[0]);
                double max = Double.parseDouble(split[1]);
                setMinValue(min);
                setMaxValue(max);
            } catch (NumberFormatException e) {
                throw new IllegalStateException(o + " is not of format 'min:max'!");
            }
        } else throw new IllegalArgumentException(o.getClass().getName() + " not of type Number or String!");
        ConfigManager.getInstance().setValue(this, true);
    }

    public void setMinValue(double d) {
        getValue().min = MathUtils.clamp(MathUtils.roundInc(d, step), min, max);
    }

    public void setMaxValue(double d) {
        getValue().max = MathUtils.clamp(MathUtils.roundInc(d, step), min, max);
    }

    @Getter
    @Setter
    public static class Range {
        Number min;
        Number max;

        public Range() {
        }

        public Range(Number min, Number max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Range{" +
              "min=" + min +
              ", max=" + max +
              '}';
        }
    }
}
