/*
package uwu.smsgamer.paste16fabric.injection.mixins.client;

import net.minecraft.client.*;
import net.minecraft.client.util.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.*;
import uwu.smsgamer.paste16fabric.injection.interfaces.render.ICamera;
import uwu.smsgamer.paste16fabric.module.defaultModules.player.BetterRotation;
import uwu.smsgamer.paste16fabric.utils.MathUtils;

@Mixin(Mouse.class)
public class MixinMouse {
    @Final
    @Shadow
    private MinecraftClient client;
    @Shadow
    private boolean leftButtonClicked;
    @Shadow
    private boolean middleButtonClicked;
    @Shadow
    private boolean rightButtonClicked;
    @Shadow
    private double x;
    @Shadow
    private double y;
    @Shadow
    private int controlLeftTicks;
    @Shadow
    private int activeButton = -1;
    @Shadow
    private boolean hasResolutionChanged = true;
    @Shadow
    private int field_1796;
    @Shadow
    private double glfwTime;
    @Final
    @Shadow
    private SmoothUtil cursorXSmoother = new SmoothUtil();
    @Final
    @Shadow
    private SmoothUtil cursorYSmoother = new SmoothUtil();
    @Shadow
    private double cursorDeltaX;
    @Shadow
    private double cursorDeltaY;
    @Shadow
    private double eventDeltaWheel;
    @Shadow
    private double lastMouseUpdateTime = Double.MIN_VALUE;
    @Shadow
    private boolean cursorLocked;

    */
/**
     * @author Sms_Gamer_3808
     *//*

    @Overwrite
    public void updateMouse() {
        double d = GlfwUtil.getTime();
        double e = d - this.lastMouseUpdateTime;
        this.lastMouseUpdateTime = d;
        if (this.cursorLocked && this.client.isWindowFocused()) {
            double f = this.client.options.mouseSensitivity * 0.6000000238418579D + 0.20000000298023224D;
            double g = f * f * f * 8.0D;
            double l;
            double m;
            if (this.client.options.smoothCameraEnabled) {
                double eg = e * g;
                double h = this.cursorXSmoother.smooth(this.cursorDeltaX * g, eg);
                double i = this.cursorYSmoother.smooth(this.cursorDeltaY * g, eg);
                l = h;
                m = i;
            } else {
                this.cursorXSmoother.clear();
                this.cursorYSmoother.clear();
                l = this.cursorDeltaX * g;
                m = this.cursorDeltaY * g;
            }

            this.cursorDeltaX = 0.0D;
            this.cursorDeltaY = 0.0D;

            int n = this.client.options.invertYMouse ? -1 : 1;

            this.client.getTutorialManager().onUpdateMouse(l, m);
            if (this.client.player != null) {
                if (BetterRotation.getInstance().implementRoll.getValue()) {
                    changeLookDirectionRoll(this.client.player, l, m * n);
                } else if (BetterRotation.getInstance().noPitchClamp.getValue()) {
                    changeLookDirectionNoClamp(this.client.player, l, m * n);
                } else {
                    this.client.player.changeLookDirection(l, m * n);
                }
            }

        } else {
            this.cursorDeltaX = 0.0D;
            this.cursorDeltaY = 0.0D;
        }
    }

    private void changeLookDirectionNoClamp(Entity entity, double cursorDeltaX, double cursorDeltaY) {
        double d = cursorDeltaY * 0.15D;
        double e = cursorDeltaX * 0.15D;
        entity.pitch = (float) ((double) entity.pitch + d);
        entity.yaw = (float) ((double) entity.yaw + e);
        entity.prevPitch = (float) ((double) entity.prevPitch + d);
        entity.prevYaw = (float) ((double) entity.prevYaw + e);
        if (entity.getVehicle() != null) {
            entity.getVehicle().onPassengerLookAround(entity);
        }
    }

    private void changeLookDirectionRoll(Entity entity, double cursorDeltaX, double cursorDeltaY) {
        float x = (float) (cursorDeltaX * 0.15F);
        float y = (float) (cursorDeltaY * 0.15F);
        ICamera camera = ((ICamera) client.gameRenderer.getCamera());

        Vec3d euler = camera.getEuler();

        double xSin = MathUtils.sin_dd(euler.x);
        double xCos = MathUtils.cos_dd(euler.x);
        double ySin = MathUtils.sin_dd(euler.y);
        double yCos = MathUtils.cos_dd(euler.y);
        double zSin = MathUtils.sin_dd(euler.z);
        double zCos = MathUtils.cos_dd(euler.z);

        switch (BetterRotation.getInstance().test.getInt()) {
            case 0: {
                camera.setEuler(euler.x + y * ySin * zSin + x * yCos * zCos,
                  euler.y + y * xCos * zCos + x * xSin * zSin,
                  euler.z + y * xCos * yCos + x * xSin * ySin);
                break;
            }
            case 1: {
                camera.setEuler(euler.x + x * ySin * zSin + y * yCos * zCos,
                  euler.y + y * xCos * zCos + x * xSin * zSin,
                  euler.z + y * xCos * yCos + x * xSin * ySin);
                break;
            }
            case 2: {
                camera.setEuler(euler.x + y * ySin * zSin + x * yCos * zCos,
                  euler.y + x * xCos * zCos + y * xSin * zSin,
                  euler.z + y * xCos * yCos + x * xSin * ySin);
                break;
            }
            case 3: {
                camera.setEuler(euler.x + x * ySin * zSin + y * yCos * zCos,
                  euler.y + x * xCos * zCos + y * xSin * zSin,
                  euler.z + y * xCos * yCos + x * xSin * ySin);
                break;
            }
            case 4: {
                camera.setEuler(euler.x + y * ySin * zSin + x * yCos * zCos,
                  euler.y + y * xCos * zCos + x * xSin * zSin,
                  euler.z + x * xCos * yCos + y * xSin * ySin);
                break;
            }
            case 5: {
                camera.setEuler(euler.x + x * ySin * zSin + y * yCos * zCos,
                  euler.y + y * xCos * zCos + x * xSin * zSin,
                  euler.z + x * xCos * yCos + y * xSin * ySin);
                break;
            }
            case 6: {
                camera.setEuler(euler.x + y * ySin * zSin + x * yCos * zCos,
                  euler.y + x * xCos * zCos + y * xSin * zSin,
                  euler.z + x * xCos * yCos + y * xSin * ySin);
                break;
            }
            case 7: {
                camera.setEuler(euler.x + x * ySin * zSin + y * yCos * zCos,
                  euler.y + x * xCos * zCos + y * xSin * zSin,
                  euler.z + x * xCos * yCos + y * xSin * ySin);
                break;
            }
        }

//        float yaw = entity.yaw;
//        float pitch = entity.pitch;
//        float roll = camera.getRoll();
//
//        float cosYaw = MathUtils.cos_fd(yaw);
//        float sinYaw = MathUtils.sin_fd(yaw);
//        float cosPitch = MathUtils.cos_fd(pitch);
//        float sinPitch = MathUtils.sin_fd(pitch);
//        float cosRoll = MathUtils.cos_fd(roll);
//        float sinRoll = MathUtils.sin_fd(roll);
//
//        entity.yaw += x * cosPitch * cosRoll + y * -sinRoll * cosPitch;
//        entity.pitch += y * cosRoll + x * sinRoll;
//
//        entity.pitch = (float)((double)entity.pitch + d);
//        entity.yaw = (float)((double)entity.yaw + e);
//        entity.prevPitch = (float)((double)entity.prevPitch + d);
//        entity.prevYaw = (float)((double)entity.prevYaw + e);
//        if (entity.getVehicle() != null) {
//            entity.getVehicle().onPassengerLookAround(entity);
//        }
    }
}
*/
