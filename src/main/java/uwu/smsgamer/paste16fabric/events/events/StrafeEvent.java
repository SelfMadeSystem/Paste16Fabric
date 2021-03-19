package uwu.smsgamer.paste16fabric.events.events;

import net.minecraft.util.math.Vec3d;
import uwu.smsgamer.paste16fabric.events.Event;

public class StrafeEvent implements Event {
    public Vec3d movementInput;
    public float speed;
    public float yaw;
    public boolean cancel;

    public StrafeEvent(Vec3d movementInput, float speed, float yaw) {
        this.movementInput = movementInput;
        this.speed = speed;
        this.yaw = yaw;
    }
}
