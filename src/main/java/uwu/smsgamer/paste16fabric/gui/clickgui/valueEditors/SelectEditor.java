package uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.values.VSelect;

public class SelectEditor<T> extends AbstractValueEditor<VSelect<T>, Integer> {
    public SelectEditor(VSelect<T> thisVal) {
        super(thisVal);
    }

    @Override
    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x, (int) y, (int) (x + width), (int) (y + height), 0xFF00FF00);
        drawCenteredString(stack, mc.textRenderer, val.getStringValue(), (int) (x + width / 2), (int) (y + height / 2 - mc.textRenderer.fontHeight / 2), 0xFF404040);
        return new Vec2f(width, height);
    }

    @Override
    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        return new Vec2f(w, 10);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            if (isMouseOver(mouseX, mouseY)) {
                if (isDragging()) {
                    setDragging(false);
                    lastGui.override = null;
                } else {
                    setDragging(true);
                    lastGui.override = this;
                }
                return true;
            } else if (isDragging()) {
                int i = getMouseVal(mouseY);
                String[] selections = val.getSelections();
                if (i >= 0 && i < selections.length) {
                    setDragging(false);
                    lastGui.override = null;
                    val.setValue(i);
                }
            }
        }
        return false;
    }

    @Override
    protected void postRender(MatrixStack stack) {
        String[] selections = val.getSelections();
        for (int i = 0; i < selections.length; i++) {
            renderThing(stack, i, selections[i]);
        }
    }

    public void renderThing(MatrixStack stack, int i, String s) {
        float x = lastX;
        float y = lastY + (height * i);
        fill(stack, (int) x, (int) y, (int) (x + width), (int) (y + height), 0xFF00FF00);
        drawCenteredString(stack, mc.textRenderer, s, (int) (x + width / 2), (int) (y + height / 2 - mc.textRenderer.fontHeight / 2), 0xFF404040);
    }

    public int getMouseVal(double mouseY) {
        return (int) ((mouseY - lastY) / height);
    }
}
