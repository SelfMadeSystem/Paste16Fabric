package uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.values.*;

public class ToggleEditor extends AbstractValueEditor<VBool, Boolean> {
    public ToggleEditor(VBool thisVal) {
        super(thisVal);
    }

    @Override
    protected Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x, (int) y, (int) (x + width), (int) (y + height), 0xFF666666);
        if (val.getValue()) {
            fill(stack, (int) x + 1, (int) y + 1, (int) (x + width - 1), (int) (y + height - 1), 0xFF00FF00);
        } else {
            fill(stack, (int) x + 1, (int) y + 1, (int) (x + width - 1), (int) (y + height - 1), 0xFFFF0000);
        }
        return new Vec2f(10, 10);
    }

    @Override
    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        return new Vec2f(10, 10);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && button == 0) {
            val.setValue(!val.getValue());
            return true;
        }
        return false;
    }
}
