package uwu.smsgamer.paste16fabric.injection.mixins.client;

import net.minecraft.client.*;
import net.minecraft.client.util.*;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.*;
import uwu.smsgamer.paste16fabric.module.defaultModules.player.BetterRotation;

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

    /**
     * @author Sms_Gamer_3808
     */
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
        double d = cursorDeltaY * 0.15D;
        double e = cursorDeltaX * 0.15D;
//        entity.pitch = (float)((double)entity.pitch + d);
//        entity.yaw = (float)((double)entity.yaw + e);
//        entity.prevPitch = (float)((double)entity.prevPitch + d);
//        entity.prevYaw = (float)((double)entity.prevYaw + e);
        if (entity.getVehicle() != null) {
            entity.getVehicle().onPassengerLookAround(entity);
        }
    }
}
