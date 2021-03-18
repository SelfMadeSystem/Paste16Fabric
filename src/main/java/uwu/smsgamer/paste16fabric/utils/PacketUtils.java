package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class PacketUtils {
    public static PlayerMoveC2SPacket removeRotation(PlayerMoveC2SPacket packet) {
        if (packet == null || packet.getClass().equals(PlayerMoveC2SPacket.PositionOnly.class)) {
            return packet;
        } else if (packet.getClass().equals(PlayerMoveC2SPacket.Both.class)) {
            return new PlayerMoveC2SPacket.PositionOnly(packet.getX(0), packet.getY(0), packet.getZ(0), packet.isOnGround());
        }
        return new PlayerMoveC2SPacket(packet.isOnGround());
    }

    public static PlayerMoveC2SPacket addRotation(PlayerMoveC2SPacket packet, boolean onGround, float yaw, float pitch) {
        if (packet == null || packet.getClass().equals(PlayerMoveC2SPacket.LookOnly.class) || packet.getClass().equals(PlayerMoveC2SPacket.class)) {
            return new PlayerMoveC2SPacket.LookOnly(yaw, pitch, onGround);
        }
        return new PlayerMoveC2SPacket.Both(packet.getX(0), packet.getY(0), packet.getZ(0), yaw, pitch, packet.isOnGround());
    }
}
