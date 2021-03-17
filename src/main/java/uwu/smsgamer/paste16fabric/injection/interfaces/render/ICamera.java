package uwu.smsgamer.paste16fabric.injection.interfaces.render;

import net.minecraft.util.math.Vec3d;

public interface ICamera {
    float getRoll();
    void setRoll(float roll);
    void setRotation(float yaw, float pitch, float roll);

    default void setEuler(double x, double y, double z) {
        setEuler(new Vec3d(x, y, z));
    }

    void setEuler(Vec3d vec);

    Vec3d getEuler();
}
