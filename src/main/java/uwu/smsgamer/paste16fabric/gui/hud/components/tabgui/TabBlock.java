package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;

public abstract class TabBlock {
    protected static final float BORDER = 2F;
    protected static final float HEIGHT = 20F;
    protected static final float WIDTH = 100F;

    protected final TabGui gui;

    protected TabBlock(TabGui gui) {
        this.gui = gui;
    }

    public abstract void render(MatrixStack matrices, float x, float y);
}
