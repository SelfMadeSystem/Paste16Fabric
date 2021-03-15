package uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.values.Val;

public class NoValueEditor<T> extends AbstractValueEditor<T> {
    public NoValueEditor(Val<T> thisVal) {
        super(thisVal);
    }

    @Override
    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, float w, float h) {
        fill(stack, (int) x, (int) y, (int) w, (int) h, 0xFFFFFFFF);
        drawCenteredString(stack, mc.textRenderer, val.getStringValue(), (int) (x + w / 2), (int) (y + h / 2), 0xFF000000);
        return new Vec2f(w, h);
    }
}
