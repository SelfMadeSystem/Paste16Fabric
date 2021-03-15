package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import org.jetbrains.annotations.Nullable;
import uwu.smsgamer.paste16fabric.module.PasteModule;
import uwu.smsgamer.paste16fabric.utils.text.GlyphPageFontRenderer;

public class TabModule extends TabBlock {
    public final PasteModule module;

    public TabModule(TabGui gui, PasteModule module) {
        super(gui);
        this.module = module;
    }

    private boolean lastState = false;

    @Override
    protected boolean shouldUpdate() {
        boolean a = module.getState();
        boolean b = lastState == a;
        lastState = a;
        return b;
    }

    @Override
    protected TabGui.ModuleOptions getOptions() {
        return gui.getModuleOptions();
    }

    @Override
    protected TabGui.Options getOpt() {
        TabGui.ModuleOptions o = getOptions();
        return module.getState() ?
          hovered ? o.getSelectedHoverOptions() : o.getSelectedOptions():
          hovered ? o.getHoverOptions() : o.getBaseOptions();
    }

    @Override
    protected String getText() {
        return module.getName();
    }

    @Override
    protected GlyphPageFontRenderer getRenderer() {
        return gui.moduleRenderer;
    }

    @Override
    public void select(@Nullable TabGui gui, @Nullable TabBlock block) {
        if (block != null) {
            block.current = null;
            module.toggle();
            this.setSelected(module.getState());
        }
    }
}
