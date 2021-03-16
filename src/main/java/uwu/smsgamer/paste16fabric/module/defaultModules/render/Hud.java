package uwu.smsgamer.paste16fabric.module.defaultModules.render;

import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.*;
import uwu.smsgamer.paste16fabric.gui.hud.HudManager;
import uwu.smsgamer.paste16fabric.module.*;

public class Hud extends PasteModule {
    public Hud() {
        super("Hud", "Heads up display", ModuleCategory.RENDER, true, -1);
        HudManager.getInstance();
    }
//    private static final TextRenderer renderer = new TextRenderer("richardson brand accelerator", 200F, 0)
//      .setVerticalAnchor(0).setHorizontalAnchor(0);

    float add;

    @PasteListener
    public void onRender(RenderEvent event) {
        if (!getState()) return;
        if (mc.textRenderer == null) return;
//        Matrix4f matrix = event.matrices.peek().getModel();
//        renderer.setHorizontalAnchor(-1);
//        renderer.drawString(matrix, "Left", -1, 0.5F, Color.GREEN);
//        renderer.setHorizontalAnchor(1);
//        renderer.drawString(matrix, "Right", 1, 0.5F, Color.GREEN);
//        renderer.setHorizontalAnchor(0);
//        renderer.setVerticalAnchor(0);
//        renderer.drawString(matrix, "Middle", 0, 0, Color.GREEN);
//        renderer.setVerticalAnchor(-1);
//        renderer.drawString(matrix, "Bottom", 0, -1F, Color.GREEN);
//        renderer.setVerticalAnchor(1);
//        renderer.drawString(matrix, "Top", 0, 1F, Color.GREEN);
        if (event.place.equals(RenderEvent.Place.HUD)) HudManager.getInstance().render(event);

//        Render2D.drawString(Render2D.identity(), "Hello world!", "consolas", 0, 0, -1, -1, true, Colours.WHITE);

//        DrawableHelper.fill(event.matrices, 0, 0, 20, 20, 0xFFFFFFFF);
//        Render2D.drawRect(event.matrices.peek().getModel(), 0, 0, 10000, 10000, Color.BLACK);
//        Render2D.drawBorderedRect(event.matrices.peek().getModel(), 50, 50, 100, 100, 5, new Color(255, 255, 255, 50), new Color(255, 50, 50, 50));
//        Matrix4f identity = Matrix4f.scale(1, 1, 1);
//        Matrix4f identity = event.matrices.peek().getModel();
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

    @PasteListener
    public void onKey(KeyPressEvent event) {
        if (!getState()) return;
        if (mc.player == null) return;
        if (mc.currentScreen == null) HudManager.getInstance().onKey(event);
    }

    @Override
    public void init() {
        super.init();
        HudManager.getInstance().init();
    }
}
