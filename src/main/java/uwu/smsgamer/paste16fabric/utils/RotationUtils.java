package uwu.smsgamer.paste16fabric.utils;

import lombok.Getter;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.*;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.SendMovementPacketsEvent;

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

        return new Box(center.x - lenX, center.y + yOffset - lenY, center.z - lenZ,
          center.x + lenX, center.y + yOffset + lenY, center.z + lenZ);
    }

    public static AimInfo getAimInfo(Matrix4f matrix, Box box, Rotation current) {
        Render3D.drawBox(matrix, box, new RGB(0, 1, 1, 0.5));

        box = box.offset(PlayerUtils.getEyePos().multiply(-1));

        AimInfo result = new AimInfo();
        Vec3d center = box.getCenter();
        result.centerRot = toRotation(center);

        double lenX = box.getXLength() * 0.5;
        double lenY = box.getYLength() * 0.5;
        double lenZ = box.getZLength() * 0.5;

        Rotation min = null;
        Rotation minDiff = null;
        Rotation max = null;
        Rotation maxDiff = null;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Vec3d pos = center.add(lenX * x, lenY * y, lenZ * z);

                    Box rb = new Box(pos.x - 0.05, pos.y - 0.05, pos.z - 0.05, pos.x + 0.05, pos.y + 0.05, pos.z + 0.05).offset(PlayerUtils.getEyePos());

                    Render3D.drawBox(matrix, rb, new RGB(1, 0, 0, 1));

                    Rotation rot = toRotation(pos);

                    if (min == null) {
                        min = max = rot;
                        minDiff = result.centerRot.diff(min);
                        maxDiff = result.centerRot.diff(max);
                    } else {
                        Rotation diff = result.centerRot.diff(rot);
                        if (diff.yaw < minDiff.yaw) {
                            min = new Rotation(rot.yaw, min.pitch);
                            minDiff = new Rotation(diff.yaw, minDiff.pitch);
                        }
                        if (diff.pitch < minDiff.pitch) {
                            min = new Rotation(min.yaw, rot.pitch);
                            minDiff = new Rotation(minDiff.yaw, diff.pitch);
                        }
                        if (diff.yaw > maxDiff.yaw) {
                            max = new Rotation(rot.yaw, max.pitch);
                            maxDiff = new Rotation(diff.yaw, maxDiff.pitch);
                        }
                        if (diff.pitch > maxDiff.pitch) {
                            max = new Rotation(max.yaw, rot.pitch);
                            maxDiff = new Rotation(maxDiff.yaw, diff.pitch);
                        }
                    }
                }
            }
        }

        boolean rYaw = !isBetweenAngles(min.yaw, max.yaw, current.yaw);
        boolean rPitch = !isBetweenAngles(min.pitch, max.pitch, current.pitch);

        if (rYaw || rPitch) {
            boolean minYaw = Math.abs(angleDiff(min.yaw, current.yaw)) < Math.abs(angleDiff(max.yaw, current.yaw));
            boolean minPitch = Math.abs(angleDiff(min.pitch, current.pitch)) < Math.abs(angleDiff(max.pitch, current.pitch));

            current = new Rotation(
              rYaw ? minYaw ? min.yaw : max.yaw : current.yaw,
              rPitch ? minPitch ? min.pitch : max.pitch : current.pitch
            );
        }

        result.minRot = min;
        result.maxRot = max;
        result.closestRot = current;

        return result;
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
        return Math.abs(angleDiff(a, r)) <= diff && Math.abs(angleDiff(b, r)) <= diff;
    }

    public static float wrapAngle180(float f) {
        return MathUtils.wrapAngle180(f);
    }

    public static class AimInfo {
        public Rotation minRot;
        public Rotation maxRot;
        public Rotation closestRot;
        public Rotation centerRot;
    }
}
