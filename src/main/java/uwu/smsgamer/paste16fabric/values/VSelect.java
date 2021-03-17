package uwu.smsgamer.paste16fabric.values;

import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste16fabric.config.ConfigManager;
import uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors.*;
import uwu.smsgamer.paste16fabric.module.PasteModule;

import java.util.Arrays;

public class VSelect<T> extends Val<Integer> {
    protected final T[] types;

    @SafeVarargs
    public VSelect(@NotNull PasteModule module, @NotNull String name, @NotNull Integer defaultValue, String description, T... types) {
        super(module, name, defaultValue, description);
        this.types = types;
    }

    @SafeVarargs
    public VSelect(@NotNull Val<?> parent, @NotNull String name, @NotNull Integer defaultValue, String description, T... types) {
        super(parent, name, defaultValue, description);
        this.types = types;
    }

    @Override
    public String getStringValue() {
        if (getValue() >= types.length) return "INVALID";
        return types[getValue()].toString();
    }

    @Override
    public void setValue(Object o) {
        if (o instanceof Integer) super.setValue(o);
        else if (o instanceof String) {
            setValue(selector(o.toString()));
        } else if (types[0].getClass().isInstance(o)) {
            for (int i = 0; i < types.length; i++) {
                if (o.equals(types[i])) setValue(i);
            }
        }
        ConfigManager.getInstance().setValue(this, true);
    }

    protected int selector(String s) {
        for (int i = 0, typesLength = types.length; i < typesLength; i++) {
            T type = types[i];
            if (type.toString().equalsIgnoreCase(s)) return i;
        }
        return getValue();
    }

    @Override
    public AbstractValueEditor<?, ?> getValueEditor() {
        return new SelectEditor<>(this);
    }

    public String[] getSelections() {
        return Arrays.stream(types).map(Object::toString).toArray(String[]::new);
    }

    public T getSelected() {
        return types[getValue()];
    }
}
