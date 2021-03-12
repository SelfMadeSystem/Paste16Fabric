package uwu.smsgamer.paste.paste16fabric.gui.hud.components.tabgui;

import uwu.smsgamer.paste.paste16fabric.module.ModuleCategory;

import java.util.List;

public class TabCategory {
    public final ModuleCategory category;
    public List<TabModule> modules;

    public TabCategory(ModuleCategory category) {
        this.category = category;
    }
}
