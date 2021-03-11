package uwu.smsgamer.paste.paste16fabric.events.events;

import uwu.smsgamer.paste.paste16fabric.events.Event;

public class RenderEvent implements Event {
    public final float partialTicks;

    public RenderEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
