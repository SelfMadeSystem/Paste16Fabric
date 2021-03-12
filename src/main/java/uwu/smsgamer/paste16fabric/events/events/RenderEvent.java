package uwu.smsgamer.paste16fabric.events.events;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.events.Event;

public class RenderEvent implements Event {
    public final float partialTicks;
    public final MatrixStack matrices;

    public RenderEvent(float partialTicks, MatrixStack matrices) {
        this.partialTicks = partialTicks;
        this.matrices = matrices;
    }
}
