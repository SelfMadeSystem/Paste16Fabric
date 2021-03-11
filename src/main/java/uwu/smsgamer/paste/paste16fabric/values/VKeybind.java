package uwu.smsgamer.paste.core.values;

import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste.core.module.PasteModule;

public class VKeybind extends Val<Integer> {
    public VKeybind(@NotNull PasteModule module, @NotNull String name, @NotNull Integer defaultValue) {
        super(module, name, defaultValue);
    }

    public VKeybind(@NotNull Val<Integer> parent, @NotNull String name, @NotNull Integer defaultValue) {
        super(parent, name, defaultValue);
    }
}
