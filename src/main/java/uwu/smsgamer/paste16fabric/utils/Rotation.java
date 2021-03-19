package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.util.math.Vec2f;

public class Rotation implements MinecraftHelper {
    public final float yaw;
    public final float pitch;

    public Rotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Rotation(Vec2f vec) {
        this(vec.x, vec.y);
    }

    public static Rotation player() {
        return new Rotation(mc.player.getRotationClient());
    }

    public void toPlayer() {
        mc.player.yaw = this.yaw;
        mc.player.pitch = this.pitch;
    }

    @Override
    public String toString() {
        return "Rotation{" +
          "yaw=" + yaw +
          ", pitch=" + pitch +
          '}';
    }
}
