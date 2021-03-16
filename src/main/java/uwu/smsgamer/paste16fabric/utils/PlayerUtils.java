package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.util.math.Vec3d;

public class PlayerUtils implements MinecraftHelper {
    public static Vec3d getEyePos() {
        return mc.player.getPos().add(0, mc.player.getEyeHeight(mc.player.getPose()), 0);
    }
}
