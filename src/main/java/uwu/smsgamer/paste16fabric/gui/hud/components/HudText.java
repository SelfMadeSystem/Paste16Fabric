package uwu.smsgamer.paste16fabric.gui.hud.components;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import uwu.smsgamer.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste16fabric.utils.*;
import uwu.smsgamer.paste16fabric.utils.text.GlyphPageFontRenderer;


@Getter
@Setter
public class HudText extends HudComponent {
    protected Colour colour = Colours.BLACK;
    protected String text = "Orem Lipsum";
    protected FontSettings fontSettings = new FontSettings();
    protected float size = 1;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected GlyphPageFontRenderer renderer;

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
        Matrix4f model = Matrix4f.scale(size, size, 1);

        float x = (getX() / size);
        float y = (getY() / size);

        if (fontSettings.font == null || fontSettings.font.isBlank()) {
            float v = getFontSettings().getBaseFontSize() / 14F;
            model.multiply(v);

            x /= v;
            y /= v;

            Render2D.drawString(model, text, x, y, getHorizontalAlignment(), getVerticalAlignment(), fontSettings.shadow, false, colour);
        } else {
            Render2D.drawString(model, text, renderer, x, y, getHorizontalAlignment(), getVerticalAlignment(), fontSettings.shadow, colour);
        }
    }

    @Override
    public float getWidth() {
        return mc.textRenderer.getWidth(text) * size;
    }

    @Override
    public float getHeight() {
        return mc.textRenderer.fontHeight * size;
    }

    public void reloadRenderer() {
        if (fontSettings.font != null && !fontSettings.font.isBlank())
            renderer = fontSettings.generateRenderer();
    }

    @Override
    public void init() {
        reloadRenderer();
    }

    @Getter
    @Setter
    public static class FontSettings {
        protected String font = "";
        protected int baseFontSize = 14;
        protected boolean shadow;
        protected boolean bold;
        protected boolean italic;

        public GlyphPageFontRenderer generateRenderer() {
            return GlyphPageFontRenderer.create(font, baseFontSize, bold, italic, bold && italic);
        }
    }
}
