package uwu.smsgamer.paste16fabric.module;

import uwu.smsgamer.paste16fabric.events.*;
import uwu.smsgamer.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste16fabric.module.defaultModules.movement.*;
import uwu.smsgamer.paste16fabric.module.defaultModules.player.BetterRotation;
import uwu.smsgamer.paste16fabric.module.defaultModules.render.*;
import uwu.smsgamer.paste16fabric.module.defaultModules.world.Nuker;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class ModuleManager implements MinecraftHelper {
    private static ModuleManager instance;

    public static ModuleManager getInstance() {
        if (instance == null) instance = new ModuleManager();
        return instance;
    }

    public ModuleManager() {
        EventManager.registerListener(this);

        addModule(new Fly());
        addModule(new Speed());
        addModule(new Step());

        addModule(BetterRotation.getInstance());

        addModule(ClickGui.getInstance());
        addModule(new Hud());
        addModule(new RollTest());

        addModule(new Nuker());
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

    public List<PasteModule> getEnabledModules() {
        return getModulesConditional(PasteModule::getState);
    }

    public List<PasteModule> getModulesConditional(Predicate<PasteModule> condition) {
        return modules.stream().filter(condition).collect(Collectors.toList());
    }

    public PasteModule getModuleByName(String name) {
        for (PasteModule module : modules) {
            if (module.getName().equals(name)) return module;
        }
        return null;
    }

    public PasteModule getModuleByNameIgnoreCase(String name) {
        name = name.toLowerCase();
        for (PasteModule module : modules)
            if (module.getName().toLowerCase().equals(name)) return module;
        return null;
    }

    @PasteListener
    private void onKey(KeyPressEvent event) {
        if (mc.player != null && event.pressType == 1) {
            for (PasteModule module : modules) {
                if (module.getKeyBinding() == event.key)
                    module.toggle();
            }
        }
    }

    public void init() {
        for (PasteModule module : modules) module.init();
    }
}
