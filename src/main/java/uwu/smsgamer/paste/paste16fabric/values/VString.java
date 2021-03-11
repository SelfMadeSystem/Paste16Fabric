package uwu.smsgamer.paste.core.values;

import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste.core.module.PasteModule;

public class VString extends Val<String> {
    public VString(@NotNull PasteModule module, @NotNull String name, @NotNull String defaultValue) {
        super(module, name, defaultValue);
    }

    public VString(@NotNull Val<String> parent, @NotNull String name, @NotNull String defaultValue) {
        super(parent, name, defaultValue);
    }
}
