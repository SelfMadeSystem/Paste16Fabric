package uwu.smsgamer.paste16fabric.module.defaultmodules.movement;

import net.minecraft.util.math.Vec3d;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.UpdateEvent;
import uwu.smsgamer.paste16fabric.module.*;

public class Speed extends PasteModule {
    public Speed() {
        super("Speed", "Lets you move faster.", ModuleCategory.MOVEMENT);
    }

    @PasteListener
    public void onUpdate(UpdateEvent event) {
        if (!getState()) return;
        Vec3d velocity = mc.player.getVelocity();
        mc.player.setVelocity(velocity.x * 1.1F, velocity.y, velocity.z * 1.1F);
    }
}
