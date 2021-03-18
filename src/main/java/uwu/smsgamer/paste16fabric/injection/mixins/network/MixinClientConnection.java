package uwu.smsgamer.paste16fabric.injection.mixins.network;

import io.netty.util.concurrent.*;
import net.minecraft.network.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import uwu.smsgamer.paste16fabric.events.EventManager;
import uwu.smsgamer.paste16fabric.events.events.PacketSendEvent;

import java.util.Queue;

@Mixin(ClientConnection.class)
public abstract class MixinClientConnection {
    @Shadow
    public abstract boolean isOpen();

    @Shadow
    protected abstract void sendQueuedPackets();

    @Shadow
    protected abstract void sendImmediately(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> callback);

    @Shadow
    @Final
    private Queue packetQueue;

    /**
     * @author Sms_Gamer_3808
     */
    @Overwrite
    public void send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> callback) {
        PacketSendEvent event = new PacketSendEvent(packet);
        EventManager.call(event);
        if (event.cancelled) return;
        packet = event.packet;
        if (this.isOpen()) {
            this.sendQueuedPackets();
            this.sendImmediately(packet, callback);
        } else {
            this.packetQueue.add(new QueuedPacket(packet, callback));
        }
    }

    @Mixin(targets = "net.minecraft.network.ClientConnection$QueuedPacket")
    static class QueuedPacket {
        private final Packet<?> packet;
        @Nullable
        private final GenericFutureListener<? extends Future<? super Void>> callback;

        public QueuedPacket(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> callback) {
            this.packet = packet;
            this.callback = callback;
        }
    }
}
