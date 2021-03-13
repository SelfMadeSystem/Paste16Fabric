package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.jetbrains.annotations.Nullable;
import uwu.smsgamer.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste16fabric.utils.*;

@Getter
@Setter
public abstract class TabBlock implements MinecraftHelper {
    protected final TabGui gui;
    protected boolean hovered;
    protected boolean selected;

    protected int hover = 0;
    @Setter
    protected TabBlock current;

    protected TabBlock(TabGui gui) {
        this.gui = gui;
    }

    protected abstract TabGui.CategoryOptions getOptions();

    protected abstract TabGui.Options getOpt();

    protected abstract String getText();

    private TabGui.Options options;

    protected TabGui.Options opt() {
        if (options == null) setOpt(true);
        return options;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
        setOpt(false);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        setOpt(false);
    }

    private long lastChange = 0;

    private void setOpt(boolean start) {
        if (!start) setLasts();
        options = getOpt();
        lastChange = System.currentTimeMillis();
    }

    private void setLasts() {
        prevBoxWidth = getBoxWidth();
        prevBoxHeight = getBoxHeight();
        prevBoxBorder = getBoxBorder();
        prevBoxInsideColour = getBoxInsideColour();
        prevBoxBorderColour = getBoxBorderColour();
        prevTextSize = getTextSize();
        prevTextColour = getTextColour();
        prevXOffset = getXOffset();
        prevYOffset = getYOffset();
    }

    public float getTime() {
        return (float) MathUtils.clamp((lastChange - System.currentTimeMillis()) / (float) -getOptions().getFade(), 0, 1);
    }

    private float prevBoxWidth = -1;

    public float getBoxWidth() {
        if (prevBoxWidth == -1) prevBoxWidth = opt().getBoxWidth();
        return prevBoxWidth + (opt().getBoxWidth() - prevBoxWidth) * getTime();
    }

    private float prevBoxHeight = -1;

    public float getBoxHeight() {
        if (prevBoxHeight == -1) prevBoxHeight = opt().getBoxHeight();
        return prevBoxHeight + (opt().getBoxHeight() - prevBoxHeight) * getTime();
    }

    private float prevBoxBorder = -1;

    public float getBoxBorder() {
        if (prevBoxBorder == -1) prevBoxBorder = opt().getBoxBorder();
        return prevBoxBorder + (opt().getBoxBorder() - prevBoxBorder) * getTime();
    }

    private Colour prevBoxInsideColour;

    public Colour getBoxInsideColour() {
        if (prevBoxInsideColour == null) prevBoxInsideColour = opt().getBoxInsideColour();
        return opt().getBoxInsideColour().interpolate(prevBoxInsideColour, getTime());
    }

    private Colour prevBoxBorderColour;

    public Colour getBoxBorderColour() {
        if (prevBoxBorderColour == null) prevBoxBorderColour = opt().getBoxBorderColour();
        return opt().getBoxBorderColour().interpolate(prevBoxBorderColour, getTime());
    }

    private float prevTextSize = -1;

    public float getTextSize() {
        if (prevTextSize == -1) prevTextSize = (float) opt().getTextSize();
        return (float) (prevTextSize + (opt().getTextSize() - prevTextSize) * getTime());
    }

    private Colour prevTextColour;

    public Colour getTextColour() {
        if (prevTextColour == null) prevTextColour = opt().getTextColour();
        return opt().getTextColour().interpolate(prevTextColour, getTime());
    }

    private float prevXOffset = -1;

    public float getXOffset() {
        if (prevXOffset == -1) prevXOffset = (float) opt().getXOffset();
        return (float) (prevXOffset + (opt().getXOffset() - prevXOffset) * getTime());
    }

    private float prevYOffset = -1;

    public float getYOffset() {
        if (prevYOffset == -1) prevYOffset = (float) opt().getYOffset();
        return (float) (prevYOffset + (opt().getYOffset() - prevYOffset) * getTime());
    }

    public void render(MatrixStack matrices, float x, float y, float top) {
        x += getXOffset();
        y += getYOffset();

        opt();
        Matrix4f model = matrices.peek().getModel().copy();
        Render2D.drawBorderedRect(model, x, y, x + getBoxWidth(), y + getBoxHeight(), getBoxBorder(),
          getBoxInsideColour(), getBoxBorderColour());

        int textHorizontalAlignment = getOptions().getTextHorizontalAlignment();
        int horizontalAlignment = textHorizontalAlignment;
        int textVerticalAlignment = getOptions().getTextVerticalAlignment();
        int verticalAlignment = textVerticalAlignment;

        switch (horizontalAlignment) {
            case -1:
                x += getBoxBorder() + 2;
                break;
            case 0:
                x += getBoxWidth() * 0.5;
                break;
            case 1:
                x += getBoxWidth() - getBoxBorder() - 2;
                break;
        }

        switch (verticalAlignment) {
            case -1:
                y += getBoxBorder() + 1;
                break;
            case 0:
                y += getBoxHeight() * 0.5;
                break;
            case 1:
                y += getBoxHeight() - getBoxBorder() - 1;
                break;
        }

        model.multiply(getTextSize());

        x /= getTextSize();
        y /= getTextSize();

        String font = getOptions().getFont();
        if (font == null || font.isBlank()) {
            Render2D.drawString(model, getText(), x, y,
              textHorizontalAlignment, textVerticalAlignment,
              getOptions().shadow, false, getTextColour());
        } else {
            Render2D.drawString(model, getText(), font, getOptions().getBaseFontSize(), x, y,
              textHorizontalAlignment, textVerticalAlignment,
              getOptions().shadow, getTextColour());
        }
    }

    public void preRender(MatrixStack matrices, float x, float y, float top) {
    }

    public void onKey(KeyPressEvent event) {
    }

    public void select(@Nullable TabGui gui, @Nullable TabBlock block) {
    }
}
