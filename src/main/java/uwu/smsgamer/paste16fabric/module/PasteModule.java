package uwu.smsgamer.paste16fabric.module;

import lombok.Getter;
import uwu.smsgamer.paste16fabric.events.EventManager;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;
import uwu.smsgamer.paste16fabric.values.*;

public class PasteModule implements MinecraftHelper {
    @Getter
    protected final String name;
    @Getter
    protected final String description;
    @Getter
    protected final ModuleCategory category;
    protected final VBool enabled;
    protected final VKeybind keyBind;

    public PasteModule(String name, String description, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = new VBool(this, "enabled", false);
        this.keyBind = new VKeybind(this, "keyBind", 0);

        EventManager.registerListener(this);
    }

    public PasteModule(String name, String description, ModuleCategory category, boolean enabled, int keybind) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = new VBool(this, "enabled", enabled);
        this.keyBind = new VKeybind(this, "keyBind", keybind);

        EventManager.registerListener(this);
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
