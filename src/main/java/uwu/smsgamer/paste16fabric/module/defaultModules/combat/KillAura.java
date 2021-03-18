package uwu.smsgamer.paste16fabric.module.defaultModules.combat;

import uwu.smsgamer.paste16fabric.events.PasteListener;
import uwu.smsgamer.paste16fabric.events.events.UpdateEvent;
import uwu.smsgamer.paste16fabric.module.*;

public class KillAura extends PasteModule {
    public KillAura() {
        super("KillAura", "Automatically hits entities around you.", ModuleCategory.COMBAT);
    }

    @PasteListener
    public void onUpdate(UpdateEvent event) {

    }
}
