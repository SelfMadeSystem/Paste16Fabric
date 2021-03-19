package uwu.smsgamer.paste16fabric.utils;

import lombok.Getter;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.*;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.*;

@Getter
public class RotationUtils implements MinecraftHelper {
    private float oYaw;
    private float oPitch;
    private boolean override;
    private boolean resend;

    private static RotationUtils instance;

    public static RotationUtils getInstance() {
        if (instance == null) new RotationUtils();
        return instance;
    }

    private RotationUtils() {
        instance = this;
    }

    public void setoYaw(float oYaw) {
        if (oYaw == this.oYaw) return;
        this.resend = true;
        this.oYaw = oYaw;
    }

    public void setoPitch(float oPitch) {
        if (this.oPitch == oPitch) return;
        this.resend = true;
        this.oPitch = oPitch;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    @PasteListener
    private void sendMovementPackets(SendMovementPacketsEvent event) {
        if (!override) return;
        if (resend) {
            event.movePacket = PacketUtils.addRotation(event.movePacket, event.onGround, oYaw, oPitch);
        } else {
            event.movePacket = PacketUtils.removeRotation(event.movePacket);
        }
    }

    public static Box getAimingBox(Box startBox, double hLimit, double vLimit, double yHeight) {
        double lenX = startBox.getXLength() * 0.5 * hLimit;
        double lenY = startBox.getYLength() * 0.5 * vLimit;
        double lenZ = startBox.getZLength() * 0.5 * hLimit;

        double yOffset = startBox.getYLength() * yHeight;

        Vec3d center = startBox.getCenter();

        return new Box(center.x - lenX, center.y - yOffset - lenY, center.z - lenZ,
          center.x + lenX, center.y - yOffset + lenY, center.z + lenZ);
    }

    public static Rotation rotationTo(Entity target) {
        return toRotation(target.getPos().subtract(PlayerUtils.getEyePos()));
    }

    public static Rotation toRotation(Vec3d vec) {
        return toRotation(vec.x, vec.y, vec.z);
    }

    public static Rotation toRotation(double x, double y, double z) {
        return new Rotation(MathUtils.wrapAngle180((float) (Math.toDegrees(Math.atan2(z, x)) - 90)),
          -MathUtils.wrapAngle180((float) Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + z * z)))));
    }

    //thx lb
    public static float angleDiff(float a, float b) {
        return MathUtils.getAngleDifference(a, b);
    }

    public static boolean isBetweenAngles(float a, float b, float r) {
        double diff = Math.abs(angleDiff(a, b));
        return Math.abs(angleDiff(a, r)) <= diff & Math.abs(angleDiff(b, r)) <= diff;
    }

    public static float wrapAngle180(float f) {
        return MathUtils.wrapAngle180(f);
    }
}
