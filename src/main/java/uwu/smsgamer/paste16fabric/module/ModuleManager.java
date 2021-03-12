package uwu.smsgamer.paste16fabric.module;

import uwu.smsgamer.paste16fabric.events.*;
import uwu.smsgamer.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste16fabric.module.defaultmodules.render.Hud;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

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
    private final EnumMap<ModuleCategory, Set<PasteModule>> modulesByCategory = new EnumMap<>(ModuleCategory.class);

    public void addModule(PasteModule module) {
        modules.add(module);
        modulesByCategory.computeIfAbsent(module.getCategory(), v -> new HashSet<>());
        modulesByCategory.get(module.getCategory()).add(module);
    }

    public Set<PasteModule> getModules() {
        return new HashSet<>(modules);
    }

    public List<PasteModule> getModulesByCategory(ModuleCategory category) {
        Set<PasteModule> pasteModules = modulesByCategory.get(category);
        if (pasteModules == null) return new LinkedList<>();
        return new LinkedList<>(pasteModules);
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

    public void init() {
        for (PasteModule module : modules) module.init();
    }
}
