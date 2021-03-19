package uwu.smsgamer.paste16fabric.injection.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;
import uwu.smsgamer.paste16fabric.events.EventManager;
import uwu.smsgamer.paste16fabric.events.events.StrafeEvent;

@Mixin(Entity.class)
public class MixinEntity {
    /**
     * @author Sms_Gamer_3808
     */
    @Overwrite
    private static Vec3d movementInputToVelocity(Vec3d movementInput, float speed, float yaw) {
        StrafeEvent event = new StrafeEvent(movementInput, speed, yaw);
        EventManager.call(event);

        if (event.cancel) return new Vec3d(0, 0, 0);

        movementInput = event.movementInput;
        speed = event.speed;
        yaw = event.yaw;

        double d = movementInput.lengthSquared();
        if (d < 1.0E-7D) return Vec3d.ZERO;

        Vec3d vec3d = (d > 1.0D ? movementInput.normalize() : movementInput).multiply(speed);
        float f = MathHelper.sin(yaw * 0.017453292F);
        float g = MathHelper.cos(yaw * 0.017453292F);
        return new Vec3d(vec3d.x * (double) g - vec3d.z * (double) f, vec3d.y, vec3d.z * (double) g + vec3d.x * (double) f);
    }
}
