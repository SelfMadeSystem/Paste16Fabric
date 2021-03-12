package uwu.smsgamer.paste.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public abstract class TabBlock {
    protected static final Color bg = Color.WHITE;
    protected static final Color fg = Color.RED;
    protected static final float BORDER = 2F;
    protected static final float HEIGHT = 20F;
    protected static final float WIDTH = 100F;

    public abstract float render(MatrixStack matrices, float x, float y);
}
