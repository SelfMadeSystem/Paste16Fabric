package uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.utils.MathUtils;
import uwu.smsgamer.paste16fabric.values.VRange;

public class RangeEditor extends AbstractValueEditor<VRange, VRange.Range> {
    private boolean max;

    public RangeEditor(VRange thisVal) {
        super(thisVal);
    }

    @Override
    protected Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x, (int) y, (int) (x + width), (int) (y + height), 0xFF666666);
        double d = width * MathUtils.clamp(val.getScaledMin(), 0, 1);
        fill(stack, (int) (x + d), (int) y,
          (int) (x + Math.max(d + 1, width * MathUtils.clamp(val.getScaledMax(), 0, 1))),
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
            double scaledMouse = getScaledMouse(mouseX);
            max = Math.abs(scaledMouse - val.getScaledMin()) > Math.abs(scaledMouse - val.getScaledMax());
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
        if (max) {
            val.setScaledMax(getScaledMouse(mouseX));
        } else {
            val.setScaledMin(getScaledMouse(mouseX));
        }
        if (val.getValue().testAndSwap()) max = !max;
    }

    public double getScaledMouse(double mouseX) {
        return (mouseX - lastX) / width;
    }
}
