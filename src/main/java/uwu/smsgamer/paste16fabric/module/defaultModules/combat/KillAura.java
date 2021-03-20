package uwu.smsgamer.paste16fabric.module.defaultModules.combat;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Matrix4f;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.*;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.utils.*;
import uwu.smsgamer.paste16fabric.values.*;

public class KillAura extends PasteModule {
    public VBool silent = new VBool(this, "Silent", false, "Aims server side but not client side.");
    public VBool strafe = new VBool(this, "Strafe", false, "Strafes properly server side.") {
        @Override
        public boolean visible() {
            return silent.getValue();
        }
    };
//    public VNumber hLimit = new VNumber.Percent(this, "Horizontal Limit", 1, 0, 2, 0.05, "Horizontal limit in % relative to the hitbox size.");
//    public VNumber vLimit = new VNumber.Percent(this, "Vertical Limit", 1, 0, 2, 0.05, "Vertical limit in % relative to the hitbox size.");
//    public VNumber yHeight = new VNumber.Percent(this, "Y Offset", 0, -1, 1, 0.05, "Vertical offset in % of the aiming box.");
    public VRange hLimitRange = new VRange(this, "Horizontal Limit", 1, 1, 0, 2, 0.05, "Horizontal limit in % relative to the hitbox size.");
    public VRange.SelectMode hLimitSelect = new VRange.SelectMode(this, "Horizontal Limit Select Mode", 0, "");
    public VRange.Time hLimit = new VRange.Time(this, "Horizontal Limit Time", "", hLimitRange, hLimitSelect);

    public VRange vLimitRange = new VRange(this, "Vertical Limit", 1, 1, 0, 2, 0.05, "Vertical limit in % relative to the hitbox size.");
    public VRange.SelectMode vLimitSelect = new VRange.SelectMode(this, "Vertical Limit Select Mode", 0, "");
    public VRange.Time vLimit = new VRange.Time(this, "Vertical Limit Time", "", vLimitRange, vLimitSelect);

    public VRange yHeightRange = new VRange(this, "Y Offset", 0, 0, -1, 1, 0.05, "Vertical offset in % of the aiming box.");
    public VRange.SelectMode yHeightSelect = new VRange.SelectMode(this, "Y Offset Select Mode", 0, "");
    public VRange.Time yHeight = new VRange.Time(this, "Y Offset Time", "", yHeightRange, yHeightSelect);

    public VSelect<String> aim = new VSelect<>(this, "Aim", 0, "Aim...?",
      "Closest",
      "Min",
      "Max",
      "Center");

    public KillAura() {
        super("KillAura", "Automatically hits entities around you.", ModuleCategory.COMBAT);
    }

    private Rotation currentR;

    @Override
    protected void onEnable() {
        if (mc.player != null) currentR = Rotation.player();
    }

    @Override
    protected void onDisable() {
        RotationUtils.getInstance().setOverride(false);
    }

    @PasteListener
    public void onUpdate(RenderEvent event) { // TODO: 2021-03-18 MouseEvent
        if (!getState()) return;
        if (event.place.equals(RenderEvent.Place.WORLD)) {
            Entity target = Targets.getClosestEntity(6, 180);
            if (target != null) {
                Rotation rotation = getRotation(event.matrices.peek().getModel(), target);
                if (currentR == null) currentR = rotation;
                else currentR = new Rotation(currentR.yaw + RotationUtils.angleDiff(rotation.yaw, currentR.yaw),
                  rotation.pitch);
                if (silent.getValue()) {
                    RotationUtils.getInstance().setOverride(true);
                    RotationUtils.getInstance().setoYaw(currentR.yaw);
                    RotationUtils.getInstance().setoPitch(currentR.pitch);
                } else {
                    RotationUtils.getInstance().setOverride(false);
                    currentR.toPlayer();
                }
            } else {
                RotationUtils.getInstance().setOverride(false);
                currentR = Rotation.player();
            }
        }
    }

    public Rotation getRotation(Matrix4f matrix, Entity target) {
        RotationUtils.AimInfo info = RotationUtils.getAimInfo(matrix, RotationUtils.getAimingBox(target.getBoundingBox(), hLimit.getModeValue(), vLimit.getModeValue(), yHeight.getModeValue()),
          silent.getValue() ? currentR : Rotation.player());
        switch (aim.getValue()) {
            case 0:
                return info.closestRot;
            case 1:
                return info.minRot;
            case 2:
                return info.maxRot;
            case 3:
                return info.centerRot;
        }
        return null;
    }

    @PasteListener
    public void onStrafe(StrafeEvent event) {
        if (!getState()) return;
        if (currentR == null) return;
        if (strafe.getValue() && silent.getValue()) event.yaw = currentR.yaw;
    }
}
