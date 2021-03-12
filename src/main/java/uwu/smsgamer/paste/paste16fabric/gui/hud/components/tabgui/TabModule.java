package uwu.smsgamer.paste.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste.paste16fabric.module.PasteModule;

public class TabModule extends TabBlock {
    public final PasteModule module;

    public TabModule(TabGui gui, PasteModule module) {
        super(gui);
        this.module = module;
    }

    @Override
    public void render(MatrixStack matrices, float x, float y) {
    }
}
