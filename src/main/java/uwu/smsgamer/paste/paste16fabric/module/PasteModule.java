package uwu.smsgamer.paste.core.module;

import lombok.Getter;
import uwu.smsgamer.paste.core.values.*;

public class PasteModule {
    @Getter
    protected final String name;
    @Getter
    protected final String description;
    protected final VBool enabled;
    protected final VKeybind keyBind;

    public PasteModule(String name, String description) {
        this.name = name;
        this.description = description;
        this.enabled = new VBool(this, "enabled", false);
        this.keyBind = new VKeybind(this, "keyBind", 0);
    }

    public PasteModule(String name, String description, boolean enabled, int keybind) {
        this.name = name;
        this.description = description;
        this.enabled = new VBool(this, "enabled", enabled);
        this.keyBind = new VKeybind(this, "keyBind", keybind);
    }

    public boolean getState() {
        return enabled.getValue();
    }

    public int getKeyBind() {
        return keyBind.getValue();
    }

    public final void toggle() {
        setState(!enabled.getValue());
    }

    public final void setState(boolean state) {
        enabled.setValue(state);
        onToggle();
        if (enabled.getValue()) onEnable();
        else onDisable();
    }

    protected void onToggle() {
    }

    protected void onEnable() {
    }

    protected void onDisable() {
    }
}
