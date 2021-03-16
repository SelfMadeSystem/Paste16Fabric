package uwu.smsgamer.paste16fabric.module.defaultModules.movement;

import uwu.smsgamer.paste16fabric.module.*;

public class Step extends PasteModule {
    public Step() {
        super("Step", "Lets you step up more than legit.", ModuleCategory.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        super.onEnable();
        if (mc.player != null) {
            mc.player.stepHeight = 2;
        }
    }

    @Override
    protected void onDisable() {
        super.onDisable();
        if (mc.player != null) mc.player.stepHeight = 0.6F;
    }
}
