package uwu.smsgamer.paste16fabric.module;

import lombok.Getter;
import uwu.smsgamer.paste16fabric.events.EventManager;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;
import uwu.smsgamer.paste16fabric.values.*;

import java.util.*;

public class PasteModule implements MinecraftHelper {
    @Getter
    protected final String name;
    @Getter
    protected final String description;
    @Getter
    protected final ModuleCategory category;
    protected final VBool enabled;
    protected final VKeybind keyBind;
    protected final List<Val<?>> values = new ArrayList<>();

    public PasteModule(String name, String description, ModuleCategory category) {
        this(name, description, category, false, -1);
    }

    public PasteModule(String name, String description, ModuleCategory category, boolean enabled, int keybind) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = new VBool(this, "enabled", enabled, "Whether this module is enabled or not.");
        this.keyBind = new VKeybind(this, "keyBind", keybind);

        EventManager.registerListener(this);
    }

    public boolean getState() {
        return enabled.getValue();
    }

    public int getKeyBinding() {
        return keyBind.getValue();
    }

    public VKeybind getKeyBind() {
        return keyBind;
    }

    public List<Val<?>> getValues() {
        return values;
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

    public void init() {
        if (getState()) onEnable();
    }
}
