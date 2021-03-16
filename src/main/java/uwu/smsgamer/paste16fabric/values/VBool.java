package uwu.smsgamer.paste16fabric.values;

import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors.*;
import uwu.smsgamer.paste16fabric.module.PasteModule;

public class VBool extends Val<Boolean> {
    public VBool(@NotNull PasteModule module, @NotNull String name, @NotNull Boolean defaultValue, String description) {
        super(module, name, defaultValue, description);
    }

    public VBool(@NotNull Val<Boolean> parent, @NotNull String name, @NotNull Boolean defaultValue, String description) {
        super(parent, name, defaultValue, description);
    }

    @Override
    public AbstractValueEditor<?> getValueEditor() {
        return new ToggleEditor(this);
    }
}
