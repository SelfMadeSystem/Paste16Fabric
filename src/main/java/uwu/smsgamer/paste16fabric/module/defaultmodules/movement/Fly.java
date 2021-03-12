package uwu.smsgamer.paste16fabric.module.defaultmodules.movement;

import net.minecraft.util.math.Vec3d;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.UpdateEvent;
import uwu.smsgamer.paste16fabric.module.*;

public class Fly extends PasteModule {
    public Fly() {
        super("Fly", "Lets fly.", ModuleCategory.MOVEMENT);
    }

    @PasteListener
    public void onUpdate(UpdateEvent event) {
        if (!getState()) return;
        Vec3d velocity = mc.player.getVelocity();
        mc.player.setVelocity(velocity.x, 0, velocity.z);
    }
}
