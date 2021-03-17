package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.util.math.Quaternion;

public class RotationUtils {
    // yaw (Z), pitch (Y), roll (X)
    public static void setQuaternion(Quaternion q, float yaw, float pitch, float roll) {
        // Abbreviations for the various angular functions
        float cy = MathUtils.cos_fd(yaw * 0.5F);
        float sy = MathUtils.sin_fd(yaw * 0.5F);
        float cp = MathUtils.cos_fd(pitch * 0.5F);
        float sp = MathUtils.sin_fd(pitch * 0.5F);
        float cr = MathUtils.cos_fd(roll * 0.5F);
        float sr = MathUtils.sin_fd(roll * 0.5F);

        q.set(cr * cp * cy + sr * sp * sy,
          sr * cp * cy - cr * sp * sy,
          cr * sp * cy + sr * cp * sy,
          cr * cp * sy - sr * sp * cy);
    }
}
