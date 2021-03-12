package uwu.smsgamer.paste16fabric.events.events;

import uwu.smsgamer.paste16fabric.events.Event;

public class CharEvent implements Event {
    public final long window;
    public final char chr;
    public final int modifiers;

    public CharEvent(long window, char chr, int modifiers) {
        this.window = window;
        this.chr = chr;
        this.modifiers = modifiers;
    }
}
