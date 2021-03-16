package uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.values.*;

public class KeybindEditor extends AbstractValueEditor<VKeybind, Integer> {
    public KeybindEditor(VKeybind thisVal) {
        super(thisVal);
    }

    @Override
    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x, (int) y, (int) (x + width), (int) (y + height), isDragging() ? 0xFF0055CC : 0xFF0088FF);
        drawCenteredString(stack, mc.textRenderer, val.getKey().toString(), (int) (x + width / 2), (int) (y + height / 2 - mc.textRenderer.fontHeight / 2), 0xFF404040);
        return new Vec2f(width, height);
    }

    @Override
    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        return new Vec2f(w, 10);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            setDragging(true);
            lastGui.override = this;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (isDragging()) {
            if (keyCode == 256) {
                val.setValue(-1);
            } else {
                val.setValue(keyCode);
            }
            setDragging(false);
            lastGui.override = null;
            return true;
        }
        return false;
    }
}
