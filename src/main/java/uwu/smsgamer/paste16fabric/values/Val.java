package uwu.smsgamer.paste16fabric.values;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste16fabric.config.ConfigValue;
import uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors.*;
import uwu.smsgamer.paste16fabric.module.PasteModule;

import java.util.ArrayList;

@Getter
public class Val<T> extends ConfigValue<T> {
    private final PasteModule module;
    private final Val<?> parent;
    private final String name;
    private final String description;
    private final ArrayList<Val<?>> vals = new ArrayList<>();

    public Val(@NotNull PasteModule module, @NotNull String name, @NotNull T defaultValue, String description) {
        super("modules." + module.getName() + "." + name, defaultValue);
        this.module = module;
        this.name = name;
        this.description = description;
        this.parent = null;
        this.module.getValues().add(this);
    }

    public Val(@NotNull Val<?> parent, @NotNull String name, @NotNull T defaultValue, String description) {
        super(parent.getPath() + "." + name, defaultValue);
        this.parent = parent;
        this.name = name;
        this.description = description;
        this.module = null;
    }

    public PasteModule getModule() {
        return module == null ? parent.getModule() : module;
    }

    public<V extends Val<?>> V addVal(V val) {
        vals.add(val);
        return val;
    }

    public String getStringValue() {
        return getValue().toString();
    }

    public AbstractValueEditor<?, ?> getValueEditor() {
        return new NoValueEditor<>(this);
    }
}
