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
    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x, (int) y, (int) (x + width), (int) (y + height), 0xFF0000FF);
        drawCenteredString(stack, mc.textRenderer, val.getStringValue(), (int) (x + width / 2), (int) (y + height / 2 - mc.textRenderer.fontHeight / 2), 0xFF404040);
        return new Vec2f(width, height);
    }

    @Override
    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        return new Vec2f(w, 10);
    }
}
