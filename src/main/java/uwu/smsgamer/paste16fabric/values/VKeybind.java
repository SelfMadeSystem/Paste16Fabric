package uwu.smsgamer.paste16fabric.values;

import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.NotNull;
import uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors.*;
import uwu.smsgamer.paste16fabric.module.PasteModule;

public class VKeybind extends Val<Integer> {
    public VKeybind(@NotNull PasteModule module, @NotNull String name, @NotNull Integer defaultValue) {
        super(module, name, defaultValue, "The keybind for this module.");
    }

    public VKeybind(@NotNull Val<?> parent, @NotNull String name, @NotNull Integer defaultValue, String description) {
        super(parent, name, defaultValue, description);
    }

    public InputUtil.Key getKey() {
        return InputUtil.fromKeyCode(getValue(), -1);
    }

    @Override
    public AbstractValueEditor<VKeybind, Integer> getValueEditor() {
        return new KeybindEditor(this);
    }
}
