package uwu.smsgamer.paste.paste16fabric.module;

import uwu.smsgamer.paste.paste16fabric.events.*;
import uwu.smsgamer.paste.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste.paste16fabric.module.defaultmodules.render.Hud;
import uwu.smsgamer.paste.paste16fabric.utils.MinecraftHelper;

import java.util.*;

public class ModuleManager implements MinecraftHelper {
    private static ModuleManager instance;

    public static ModuleManager getInstance() {
        if (instance == null) instance = new ModuleManager();
        return instance;
    }

    public ModuleManager() {
        EventManager.registerListener(this);
        addModule(new Hud());
    }

    private final Set<PasteModule> modules = new HashSet<>();

    public void addModule(PasteModule module) {
        modules.add(module);
    }

    public Set<PasteModule> getModules() {
        return new HashSet<>(modules);
    }

    @PasteListener
    private void onKey(KeyPressEvent event) {
        if (mc.player != null && event.pressType == 1) {
            for (PasteModule module : modules) {
                if (module.keyBind.getValue() == event.key)
                    module.toggle();
            }
        }
    }
}
