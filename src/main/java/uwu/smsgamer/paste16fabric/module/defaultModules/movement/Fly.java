package uwu.smsgamer.paste16fabric.module.defaultModules.movement;

import net.minecraft.util.math.Vec3d;
import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.UpdateEvent;
import uwu.smsgamer.paste16fabric.injection.interfaces.render.ICamera;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.utils.*;
import uwu.smsgamer.paste16fabric.values.*;

public class Fly extends PasteModule {
    public VSelect<String> mode = new VSelect<>(this, "Mode", 0, "The mode of the fly.",
      "yMotion",
      "Creative",
      "3D",
      "Space");

    public final VNumber speed = new VNumber(this, "Speed", 1, 0, 2, 0.005,
      "Speed for Creative, 3D & Space.") {
        @Override
        public boolean visible() {
            return mode.getValue() >= 1;
        }
    };

    public final VNumber friction = new VNumber(this, "Friction", 0.9, 0, 1.2, 0.005,
      "Friction for Space.") {
        @Override
        public boolean visible() {
            return mode.getValue() == 3;
        }
    };

    public Fly() {
        super("Fly", "Lets fly.", ModuleCategory.MOVEMENT);
    }

    private Vec3d spaceSpeed = new Vec3d(0, 0, 0);
    private float initFlyingSpeed = 0.02F;
    private float initFlySpeed = 0.02F;

    @Override
    protected void onEnable() {
        super.onEnable();
        if (mc.player != null) {
            initFlyingSpeed = mc.player.flyingSpeed;
            initFlySpeed = mc.player.abilities.getFlySpeed();
            spaceSpeed = mc.player.getVelocity();
        }
    }

    @PasteListener
    public void onUpdate(UpdateEvent event) {
        if (!getState()) return;
        assert mc.player != null;
        Vec3d velocity = mc.player.getVelocity();
        switch (mode.getValue()) {
            case 0:
                mc.player.setVelocity(velocity.x, 0, velocity.z);
                break;
            case 1:
                mc.player.flyingSpeed = 0.02F * speed.getFloat();
                mc.player.abilities.setFlySpeed(0.05F * speed.getFloat());
                mc.player.abilities.flying = true;
                break;
            case 2: {
                Vec3d frwdVec = MathUtils.rotToVec(-mc.player.yaw - 90, -mc.player.pitch);
                frwdVec = frwdVec.multiply(mc.player.forwardSpeed);
                Vec3d sideVec = MathUtils.rotToVec(-mc.player.yaw, 0);
                sideVec = sideVec.multiply(mc.player.sidewaysSpeed).multiply(1, 0, 1);
                frwdVec = frwdVec.add(sideVec);
                frwdVec = frwdVec.normalize();

                if (mc.player.forwardSpeed == 0 && mc.player.sidewaysSpeed == 0) frwdVec = new Vec3d(0, 0, 0);

                mc.player.setVelocity(frwdVec.multiply(0.2 * speed.getDouble()));
                mc.player.forwardSpeed = 0;
                mc.player.sidewaysSpeed = 0;
                break;
            }
            case 3:{
                Vec3d frwdVec = MathUtils.rotToVec(-mc.player.yaw - 90, -mc.player.pitch);
                frwdVec = frwdVec.multiply(mc.player.forwardSpeed);
                Vec3d sideVec = MathUtils.rotToVec(-mc.player.yaw, 0);
                sideVec = sideVec.multiply(mc.player.sidewaysSpeed).multiply(1, 0, 1);
                frwdVec = frwdVec.add(sideVec);
                frwdVec = frwdVec.normalize();

                if (mc.player.forwardSpeed == 0 && mc.player.sidewaysSpeed == 0) frwdVec = new Vec3d(0, 0, 0);

                spaceSpeed = spaceSpeed.add(frwdVec.multiply(0.2 * speed.getDouble())).multiply(friction.getDouble());

                if (!mc.player.noClip) spaceSpeed = EntityUtils.adjustMovementForCollisions(mc.player, spaceSpeed);

                mc.player.setVelocity(spaceSpeed);
                mc.player.forwardSpeed = 0;
                mc.player.sidewaysSpeed = 0;
                break;
            }
        }
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) {
            mc.player.abilities.flying = false;
            mc.player.flyingSpeed = initFlyingSpeed;
            mc.player.abilities.setFlySpeed(initFlySpeed);
        }
    }
}
