package uwu.smsgamer.paste16fabric.gui.hud.components;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.gui.hud.HudComponent;

import java.awt.*;


public class Text extends HudComponent {
    @Getter
    @Setter
    protected Color color;
    @Getter
    @Setter
    protected String text;

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
    }
}
