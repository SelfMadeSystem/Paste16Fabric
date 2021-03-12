package uwu.smsgamer.paste.paste16fabric.module.defaultmodules.render;

import uwu.smsgamer.paste.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste.paste16fabric.events.events.RenderEvent;
import uwu.smsgamer.paste.paste16fabric.gui.hud.HudManager;
import uwu.smsgamer.paste.paste16fabric.module.*;

public class Hud extends PasteModule {
    public Hud() {
        super("Hud", "Heads up display", ModuleCategory.RENDER, true, 0);
    }

    @PasteListener
    public void onRender(RenderEvent event) {
        if (!getState()) return;
        HudManager.getInstance().render(event);
//        Matrix4f identity = Matrix4f.scale(1, 1, 1);
//        add += event.partialTicks * 5;
//        Render2D.drawHollowCircleSegm(identity, 0f, 0f, 1f, 0.2f,
//          -80 + add, 80 + add, -88 + add, 88 + add,
//          Color.WHITE);
//        Render2D.drawHollowCircleSegm(identity, 0f, 0f, 1f, 0.2f,
//          100 + add, 260 + add, 92 + add, 268 + add,
//          Color.RED);
//        Render2D.drawCircle(identity, 0, 0, 0.15f, Color.GRAY);
//        Render2D.drawBorderedRect(identity, 0, 0, 0.5f, 0.5f, 0.05f, new Color(155, 155, 155, 50), new Color(255, 155, 155, 50));
    }
}
