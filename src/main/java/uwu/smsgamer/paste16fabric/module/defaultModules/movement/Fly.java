package uwu.smsgamer.paste16fabric.module.defaultModules.movement;

import net.minecraft.util.math.Vec3d;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.UpdateEvent;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.values.VSelect;

public class Fly extends PasteModule {
    public VSelect<String> mode = new VSelect<>(this, "Mode", 0, "The mode of the fly.",
      "yMotion",
      "Creative",
      "Space");

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
