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
    private final String description;

    public Val(@NotNull PasteModule module, @NotNull String name, @NotNull T defaultValue, String description) {
        super("modules." + module.getName() + "." + name, defaultValue);
        this.module = module;
        this.name = name;
        this.description = description;
        this.parent = null;
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
}
