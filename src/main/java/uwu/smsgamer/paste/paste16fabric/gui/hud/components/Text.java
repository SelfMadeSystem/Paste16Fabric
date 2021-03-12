package uwu.smsgamer.paste.paste16fabric.gui.hud.components;

import lombok.*;
import uwu.smsgamer.paste.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste.paste16fabric.utils.Render2D;

import java.awt.*;


public class Text extends HudComponent {
    @Getter
    @Setter
    protected Color color;
    @Getter
    @Setter
    protected String text;

    @Override
    public void onRender(float partialTicks) {
    }
}
