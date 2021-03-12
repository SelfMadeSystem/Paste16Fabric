package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.module.*;

import java.util.*;
import java.util.stream.Collectors;

public class TabCategory extends TabBlock {
    public final ModuleCategory category;
    public List<TabModule> modules;

    public TabCategory(TabGui gui, ModuleCategory category) {
        super(gui);
        this.category = category;
        this.modules = ModuleManager.getInstance().getModulesByCategory(category).stream().map(m -> new TabModule(gui, m)).collect(Collectors.toList());
        this.modules.sort(Comparator.comparing(a -> a.module.getName()));
    }

    @Override
    protected TabGui.SelectedOptions getOptions() {
        return gui.getCategoryOptions();
    }

    @Override
    protected String getText() {
        return category.getName();
    }

    @Override
    public void render(MatrixStack matrices, float x, float y, float top) {
        super.render(matrices, x, y, top);

//        if (this.selected) {
//            for (TabModule module : modules) {
//
//            }
//        }
    }
}
