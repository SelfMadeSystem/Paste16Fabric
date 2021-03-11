package uwu.smsgamer.paste.paste16fabric.module;

import java.util.*;

public class ModuleManager {
    private static ModuleManager instance;

    public static ModuleManager getInstance() {
        if (instance == null) instance = new ModuleManager();
        return instance;
    }

    private final Set<PasteModule> modules = new HashSet<>();

    public void addModule(PasteModule module) {
        modules.add(module);
    }

    public Set<PasteModule> getModules() {
        return new HashSet<>(modules);
    }
}
