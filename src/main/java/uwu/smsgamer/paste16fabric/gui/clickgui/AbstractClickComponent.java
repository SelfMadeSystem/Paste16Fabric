package uwu.smsgamer.paste16fabric.gui.clickgui;

import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

public abstract class AbstractClickComponent extends AbstractParentElement implements MinecraftHelper {
    protected float width;
    protected float height;

    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, float w, float h) {
        Vec2f size = getSize(gui, w, h);
        this.width = size.x;
        this.height = size.y;
        return render(stack, gui, x, y);
    }

    public abstract Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y);

    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        return new Vec2f(w, h);
    }
}
