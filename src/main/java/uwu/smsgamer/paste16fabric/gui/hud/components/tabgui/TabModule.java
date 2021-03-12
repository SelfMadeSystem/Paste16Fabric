package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import org.jetbrains.annotations.Nullable;
import uwu.smsgamer.paste16fabric.module.PasteModule;

public class TabModule extends TabBlock {
    public final PasteModule module;

    public TabModule(TabGui gui, PasteModule module) {
        super(gui);
        this.module = module;
    }

    @Override
    protected TabGui.SelectedOptions getOptions() {
        return gui.getModuleOptions();
    }

    @Override
    protected String getText() {
        return module.getName();
    }

    @Override
    public void select(@Nullable TabGui gui, @Nullable TabBlock block) {
        if (block != null) {
            block.setCurrent(null);
            module.toggle();
            this.setSelected(module.getState());
        }
    }
}
