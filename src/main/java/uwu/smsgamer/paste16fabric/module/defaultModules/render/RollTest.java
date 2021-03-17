package uwu.smsgamer.paste16fabric.module.defaultModules.render;

import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.RenderEvent;
import uwu.smsgamer.paste16fabric.injection.interfaces.render.ICamera;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.values.VNumber;

public class RollTest extends PasteModule {
    public final VNumber roll = new VNumber(this, "Roll", 0, -360, 360, 15,
      "Roll.");

    public RollTest() {
        super("RollTest", "Testing roll.", ModuleCategory.RENDER);
    }

    @PasteListener
    public void onRender(RenderEvent event) {
        if (!getState()) return;
        if (event.place == RenderEvent.Place.POST) {
            ((ICamera) mc.gameRenderer.getCamera()).setRoll(roll.getFloat());
        }
    }
}
