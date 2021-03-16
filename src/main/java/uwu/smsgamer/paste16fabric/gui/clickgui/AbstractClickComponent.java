package uwu.smsgamer.paste16fabric.gui.clickgui;

import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

public abstract class AbstractClickComponent extends AbstractParentElement implements MinecraftHelper {
    protected float width;
    protected float height;
    protected float lastX;
    protected float lastY;
    protected AbstractClickGui lastGui;

    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY, float w, float h) {
        Vec2f size = getSize(gui, w, h);
        this.width = size.x;
        this.height = size.y;
        this.lastX = x;
        this.lastY = y;
        this.lastGui = gui;
        return render(stack, gui, x, y, mouseX, mouseY);
    }

    protected abstract Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY);

    public abstract Vec2f getSize(AbstractClickGui gui, float w, float h);

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= lastX && mouseX <= lastX + width &&
          mouseY >= lastY && mouseY <= lastY + height;
    }
}
