package uwu.smsgamer.paste16fabric.events.events;

import net.minecraft.network.Packet;
import uwu.smsgamer.paste16fabric.events.Event;

public class PacketSendEvent implements Event {
    public Packet<?> packet;
    public boolean cancelled;

    public PacketSendEvent(Packet<?> packet) {
        this.packet = packet;
    }
}
