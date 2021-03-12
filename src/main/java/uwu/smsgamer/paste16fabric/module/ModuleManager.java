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

    // Lists because order matters.
    private final List<PasteModule> modules = new LinkedList<>();
    private final EnumMap<ModuleCategory, List<PasteModule>> modulesByCategory = new EnumMap<>(ModuleCategory.class);

    public void addModule(PasteModule module) {
        modules.add(module);
        modulesByCategory.computeIfAbsent(module.getCategory(), v -> new LinkedList<>());
        modulesByCategory.get(module.getCategory()).add(module);
    }

    public List<PasteModule> getModules() {
        return new LinkedList<>(modules);
    }

    public List<PasteModule> getModulesByCategory(ModuleCategory category) {
        List<PasteModule> pasteModules = modulesByCategory.get(category);
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
