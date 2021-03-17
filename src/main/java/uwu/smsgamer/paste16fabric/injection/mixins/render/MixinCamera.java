package uwu.smsgamer.paste16fabric.injection.mixins.render;

import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.*;
import uwu.smsgamer.paste16fabric.injection.interfaces.render.ICamera;

@Mixin(Camera.class)
public class MixinCamera implements ICamera {
    @Shadow
    private boolean ready;
    @Shadow
    private BlockView area;
    @Shadow
    private Entity focusedEntity;
    @Shadow
    @Final
    private Vector3f horizontalPlane;
    @Shadow
    @Final
    private Vector3f verticalPlane;
    @Shadow
    @Final
    private Vector3f diagonalPlane;
    @Shadow
    private float pitch;
    @Shadow
    private float yaw;
    @Shadow
    @Final
    private Quaternion rotation;
    @Shadow
    private boolean thirdPerson;
    @Shadow
    private boolean inverseView;
    @Shadow
    private float cameraY;
    @Shadow
    private float lastCameraY;

    private float roll;

    private Vec3d euler = new Vec3d(0, 0, 0);

    /**
     * @author Sms_Gamer_3808
     */
    @Overwrite
    public void update(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {
        this.ready = true;
        this.area = area;
        this.focusedEntity = focusedEntity;
        this.thirdPerson = thirdPerson;
        this.inverseView = inverseView;
        this.setRotation(focusedEntity.getYaw(tickDelta), focusedEntity.getPitch(tickDelta), this.roll);
        this.setPos(MathHelper.lerp(tickDelta, focusedEntity.prevX, focusedEntity.getX()), MathHelper.lerp(tickDelta, focusedEntity.prevY, focusedEntity.getY()) + (double) MathHelper.lerp(tickDelta, this.lastCameraY, this.cameraY), MathHelper.lerp(tickDelta, focusedEntity.prevZ, focusedEntity.getZ()));
        if (thirdPerson) {
            if (inverseView) {
                this.setRotation(this.yaw + 180.0F, -this.pitch, this.roll);
            }

            this.moveBy(-this.clipToSpace(4.0D), 0.0D, 0.0D);
        } else if (focusedEntity instanceof LivingEntity && ((LivingEntity) focusedEntity).isSleeping()) {
            Direction direction = ((LivingEntity) focusedEntity).getSleepingDirection();
            this.setRotation(direction != null ? direction.asRotation() - 180.0F : 0.0F, 0.0F, this.roll);
            this.moveBy(0.0D, 0.3D, 0.0D);
        }
    }

    /*
     * @author Sms_Gamer_3808
    @Overwrite
    protected void setRotation(float yaw, float pitch) {
        setRotation(yaw, pitch, roll);
    }
     */

    @Shadow
    private double clipToSpace(double v) {
        return 0;
    }

    @Shadow
    protected void setPos(double lerp, double v, double lerp1) {
    }

    @Shadow
    protected void moveBy(double x, double y, double z) {
    }

    @Override
    public float getRoll() {
        return this.roll;
    }

    @Override
    public void setRoll(float roll) {
        this.roll = roll;
    }

    @Override
    public void setRotation(float yaw, float pitch, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
        this.rotation.set(0.0F, 0.0F, 0.0F, 1.0F);
        this.rotation.hamiltonProduct(Vector3f.POSITIVE_X.getDegreesQuaternion(-yaw));
        this.rotation.hamiltonProduct(Vector3f.POSITIVE_Y.getDegreesQuaternion(pitch));
//        this.rotation.hamiltonProduct(Vector3f.POSITIVE_Z.getDegreesQuaternion(roll));
//        RotationUtils.setQuaternion(this.rotation, yaw + 180, pitch, roll);
        this.horizontalPlane.set(0.0F, 0.0F, 1.0F);
        this.horizontalPlane.rotate(this.rotation);
        this.verticalPlane.set(0.0F, 1.0F, 0.0F);
        this.verticalPlane.rotate(this.rotation);
        this.diagonalPlane.set(1.0F, 0.0F, 0.0F);
        this.diagonalPlane.rotate(this.rotation);
    }

    @Override
    public void setEuler(Vec3d vec) {
        euler = vec;
    }

    @Override
    public Vec3d getEuler() {
        return euler;
    }
}
