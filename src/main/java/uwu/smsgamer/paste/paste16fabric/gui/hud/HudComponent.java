package uwu.smsgamer.paste.paste16fabric.gui.hud;

import lombok.*;
import uwu.smsgamer.paste.paste16fabric.utils.MinecraftHelper;

@Getter
@Setter
public abstract class HudComponent implements MinecraftHelper {
    protected int horizontalAlignment;
    protected int verticalAlignment;
    protected double xOffset;
    protected double yOffset;

    public abstract void onRender(float partialTicks);

    private static double getRatio() {
        return mc.getWindow().getWidth() / (double) mc.getWindow().getHeight();
    }

    public double getX() {
        switch (horizontalAlignment) {
            case -1:
                return (-1 * getRatio()) + xOffset;
            case 0:
                return (0 * getRatio()) + xOffset;
            case 1:
                return (1 * getRatio()) + xOffset;
            default:
                throw new IllegalStateException("HorizontalAlignment is set to: " + horizontalAlignment);
        }
    }

    public double getY() {
        switch (verticalAlignment) {
            case -1:
                return -1 + yOffset;
            case 0:
                return 0 + yOffset;
            case 1:
                return 1 + yOffset;
            default:
                throw new IllegalStateException("VerticalAlignment is set to: " + verticalAlignment);
        }
    }
}
