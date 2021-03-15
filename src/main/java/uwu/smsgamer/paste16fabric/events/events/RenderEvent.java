package uwu.smsgamer.paste16fabric.events.events;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.events.Event;

public class RenderEvent implements Event {
    public final float partialTicks;
    public final MatrixStack matrices;
    public final Place place;

    public RenderEvent(float partialTicks, MatrixStack matrices, Place place) {
        this.partialTicks = partialTicks;
        this.matrices = matrices;
        this.place = place;
    }

    public enum Place {
        HUD,
        POST
    }
}
