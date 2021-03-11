package uwu.smsgamer.paste.paste16fabric.values;

import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste.paste16fabric.module.PasteModule;

public class VBool extends Val<Boolean> {
    public VBool(@NotNull PasteModule module, @NotNull String name, @NotNull Boolean defaultValue) {
        super(module, name, defaultValue);
    }

    public VBool(@NotNull Val<Boolean> parent, @NotNull String name, @NotNull Boolean defaultValue) {
        super(parent, name, defaultValue);
    }
}
