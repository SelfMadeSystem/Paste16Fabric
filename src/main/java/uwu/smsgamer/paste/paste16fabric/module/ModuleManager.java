package uwu.smsgamer.paste.paste16fabric.module;

import uwu.smsgamer.paste.paste16fabric.module.defaultmodules.render.Hud;

import java.util.*;

public class ModuleManager {
    private static ModuleManager instance;

    public static ModuleManager getInstance() {
        if (instance == null) instance = new ModuleManager();
        return instance;
    }

    public ModuleManager() {
        addModule(new Hud());
    }

    private final Set<PasteModule> modules = new HashSet<>();

    public void addModule(PasteModule module) {
        modules.add(module);
    }

    public Set<PasteModule> getModules() {
        return new HashSet<>(modules);
    }
}
