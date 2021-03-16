package uwu.smsgamer.paste16fabric.module.defaultModules.movement;

import net.minecraft.util.math.Vec3d;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.UpdateEvent;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.values.*;

public class Speed extends PasteModule {
    public final VNumber speed = new VNumber(this, "Speed", 1.1, 0, 2, 0.01,
      "Speed multiplier.");
    public final VRange idk = new VRange(this, "idk", 0.9, 1.1, 0, 2, 0.01,
      "Speed multiplier.");

    public Speed() {
        super("Speed", "Lets you move faster.", ModuleCategory.MOVEMENT);
    }

    @PasteListener
    public void onUpdate(UpdateEvent event) {
        if (!getState()) return;
        Vec3d velocity = mc.player.getVelocity();
        mc.player.setVelocity(velocity.x * speed.getDouble(), velocity.y, velocity.z * speed.getDouble());
    }
}
