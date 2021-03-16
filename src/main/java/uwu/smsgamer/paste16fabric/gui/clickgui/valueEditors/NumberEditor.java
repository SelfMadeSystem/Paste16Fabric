package uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.utils.MathUtils;
import uwu.smsgamer.paste16fabric.values.VNumber;

public class NumberEditor extends AbstractValueEditor<Number> {
    public NumberEditor(VNumber thisVal) {
        super(thisVal);
    }

    @Override
    protected Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x, (int) y, (int) (x + width), (int) (y + height), 0xFF666666);
        fill(stack, (int) x, (int) y,
          (int) (x + width * MathUtils.clamp(((VNumber) val).getScaledValue(), 0, 1)),
          (int) (y + height), 0xFF66FFFF);
        drawCenteredString(stack, mc.textRenderer, val.getStringValue(), (int) (x + width / 2), (int) (y + height / 2 - mc.textRenderer.fontHeight / 2), 0xFF404040);
        return new Vec2f(width, height);
    }

    @Override
    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        return new Vec2f(w, 10);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && button == 0) {
            setValue(mouseX);
            setDragging(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (isDragging()) setDragging(false);
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isDragging()) setValue(mouseX);
        return true;
    }

    public void setValue(double mouseX) {
        ((VNumber) val).setScaledValue((mouseX - lastX) / width);
    }
}
