package uwu.smsgamer.paste16fabric.module.defaultModules.combat;

import net.minecraft.entity.Entity;
import uwu.smsgamer.paste16fabric.module.*;

public class Targets extends PasteModule {
    private static Targets instance;

    public static Targets getInstance() {
        if (instance == null) new Targets();
        return instance;
    }

    private Targets() {
        super("Targets", "Options on which targets to select.", ModuleCategory.COMBAT);
        instance = this;
    }

    public static Entity getClosestEntity(double maxRange, double maxAngle) {
        double lowestDistance = maxRange;
        Entity returnEntity = null;
        for (Entity entity : mc.world.getEntities()) {
            if (!isValid(entity)) continue;
            double dist = entity.distanceTo(mc.player);
            if (dist < lowestDistance) {// && Math.abs(new RotationUtil(entity).getRotation().playerYawDiff()) <= maxAngle) {
                returnEntity = entity;
                lowestDistance = dist;
            }
        }
        return returnEntity;
    }

    private static boolean isValid(Entity entity) {
        return entity != mc.player;
    }
}
