package uwu.smsgamer.paste16fabric.injection.interfaces.render;

public interface ICamera {
    float getRoll();
    void setRoll(float roll);
    void setRotation(float yaw, float pitch, float roll);
}
