package uwu.smsgamer.paste16fabric.events.events;

import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.*;
import org.jetbrains.annotations.Nullable;
import uwu.smsgamer.paste16fabric.events.Event;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

public class SendMovementPacketsEvent implements MinecraftHelper, Event {
    public boolean onGround;
    public ClientCommandC2SPacket sprintPacket;
    public ClientCommandC2SPacket sneakPacket;
    public PlayerMoveC2SPacket movePacket;

    public SendMovementPacketsEvent(boolean onGround) {
        this.onGround = onGround;
    }

    public void sendAllPackets() {
        sendPacket(sprintPacket);
        sendPacket(sneakPacket);
        sendPacket(movePacket);
    }

    private static void sendPacket(@Nullable Packet<?> packet) {
        if (packet != null) {
            assert mc.player != null;
            mc.player.networkHandler.sendPacket(packet);
        }
    }
}
