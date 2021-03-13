package uwu.smsgamer.paste16fabric.gui.hud.components;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import uwu.smsgamer.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste16fabric.utils.*;


@Getter
@Setter
public class TextComponent extends HudComponent {
    protected Colour colour = Colours.BLACK;
    protected String text = "Orem Lipsum";
    protected String font = "";
    protected int baseFontSize = 10;
    protected double size = 1;
    protected boolean shadow = true;

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
        Matrix4f model = matrices.peek().getModel().copy();
        model.multiply((float) size);

        float x = (float) (getX() / size);
        float y = (float) (getY() / size);

        if (font == null || font.isBlank()) {
            Render2D.drawString(model, text, x, y, getHorizontalAlignment(), getVerticalAlignment(), shadow, false, colour);
        } else {
            Render2D.drawString(model, text, font, baseFontSize, x, y, getHorizontalAlignment(), getVerticalAlignment(), shadow, colour);
        }
    }

    @Override
    public float getWidth() {
        return (float) (mc.textRenderer.getWidth(text) * size);
    }

    @Override
    public float getHeight() {
        return (float) (mc.textRenderer.fontHeight * size);
    }
}
