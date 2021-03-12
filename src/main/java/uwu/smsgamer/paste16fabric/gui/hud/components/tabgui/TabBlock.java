package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

public abstract class TabBlock implements MinecraftHelper {
    protected final TabGui gui;
    public boolean hovered;
    public boolean selected;

    protected TabBlock(TabGui gui) {
        this.gui = gui;
    }

    public abstract void render(MatrixStack matrices, float x, float y);

    protected TabGui.Options opt() {
        return gui.getOptions();
    }
}
