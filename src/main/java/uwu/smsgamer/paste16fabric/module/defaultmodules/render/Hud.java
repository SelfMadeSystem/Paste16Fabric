package uwu.smsgamer.paste16fabric.module.defaultmodules.render;

import net.minecraft.client.Keyboard;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.util.InputUtil;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.*;
import uwu.smsgamer.paste16fabric.gui.hud.HudManager;
import uwu.smsgamer.paste16fabric.module.*;

public class Hud extends PasteModule {
    public Hud() {
        super("Hud", "Heads up display", ModuleCategory.RENDER, true, 0);
        HudManager.getInstance();
    }

    float add;

    @PasteListener
    public void onRender(RenderEvent event) {
        if (!getState()) return;
        if (mc.textRenderer == null) return;
        HudManager.getInstance().render(event);

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
        System.out.println(event.key + ":" + event.pressType);
        HudManager.getInstance().onKey(event);
    }
}
