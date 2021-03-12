package uwu.smsgamer.paste.paste16fabric.gui.hud;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste.paste16fabric.utils.MinecraftHelper;

@Getter
@Setter
public abstract class HudComponent implements MinecraftHelper {
    protected int horizontalAlignment;
    protected int verticalAlignment;
    protected float xOffset;
    protected float yOffset;

    public abstract void onRender(MatrixStack matrices, float partialTicks);

    public float getX() {
        switch (horizontalAlignment) {
            case -1:
                return xOffset;
            case 0:
                return mc.getWindow().getWidth()/2F + xOffset;
            case 1:
                return mc.getWindow().getWidth() + xOffset;
            default:
                throw new IllegalStateException("HorizontalAlignment is set to: " + horizontalAlignment);
        }
    }

    public float getY() {
        switch (verticalAlignment) {
            case -1:
                return yOffset;
            case 0:
                return mc.getWindow().getHeight()/2F + yOffset;
            case 1:
                return mc.getWindow().getHeight() + yOffset;
            default:
                throw new IllegalStateException("VerticalAlignment is set to: " + verticalAlignment);
        }
    }
}
