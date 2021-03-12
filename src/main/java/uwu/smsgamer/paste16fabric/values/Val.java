package uwu.smsgamer.paste16fabric.values;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste16fabric.config.ConfigValue;
import uwu.smsgamer.paste16fabric.module.PasteModule;

@Getter
public class Val<T> extends ConfigValue<T> {
    private final PasteModule module;
    private final Val<?> parent;
    private final String name;

    public Val(@NotNull PasteModule module, @NotNull String name, @NotNull T defaultValue) {
        super("modules." + module.getName() + "." + name, defaultValue);
        this.module = module;
        this.name = name;
        this.parent = null;
    }

    public Val(@NotNull Val<?> parent, @NotNull String name, @NotNull T defaultValue) {
        super(parent.getPath() + "." + name, defaultValue);
        this.parent = parent;
        this.name = name;
        this.module = null;
    }

    public PasteModule getModule() {
        return module == null ? parent.getModule() : module;
    }
}
