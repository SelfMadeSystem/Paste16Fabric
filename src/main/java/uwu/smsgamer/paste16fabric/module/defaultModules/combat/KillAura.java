package uwu.smsgamer.paste16fabric.module.defaultModules.combat;

import net.minecraft.entity.Entity;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.*;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.utils.*;
import uwu.smsgamer.paste16fabric.values.VBool;

public class KillAura extends PasteModule {
    public VBool silent = new VBool(this, "Silent", false, "Aims server side but not client side.");
    public VBool strafe = new VBool(this, "Strafe", false, "Strafes properly server side.") {
        @Override
        public boolean visible() {
            return silent.getValue();
        }
    };

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
    public void onUpdate(UpdateEvent event) { // TODO: 2021-03-18 MouseEvent
        if (!getState()) return;
        Entity target = Targets.getClosestEntity(6, 180);
        if (target != null) {
            Rotation rotation = RotationUtils.rotationTo(target);
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
            currentR = Rotation.player();
        }
    }

    @PasteListener
    public void onStrafe(StrafeEvent event) {
        if (!getState()) return;
        if (currentR == null) return;
        if (strafe.getValue() && silent.getValue()) event.yaw = currentR.yaw;
    }
}
