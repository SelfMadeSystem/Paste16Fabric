package uwu.smsgamer.paste16fabric.values;

import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste16fabric.module.PasteModule;

public class VString extends Val<String> {
    public VString(@NotNull PasteModule module, @NotNull String name, @NotNull String defaultValue, String description) {
        super(module, name, defaultValue, description);
    }

    public VString(@NotNull Val<String> parent, @NotNull String name, @NotNull String defaultValue, String description) {
        super(parent, name, defaultValue, description);
    }
}
