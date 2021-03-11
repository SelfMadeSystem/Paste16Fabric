package uwu.smsgamer.paste.paste16fabric.events.events;

import uwu.smsgamer.paste.paste16fabric.events.Event;

public class KeyPressEvent implements Event {
    public final long window;
    public final int key, scancode, pressType, modifiers;

    public KeyPressEvent(long window, int key, int scancode, int pressType, int modifiers) {
        this.window = window;
        this.key = key;
        this.scancode = scancode;
        this.pressType = pressType;
        this.modifiers = modifiers;
    }
}
