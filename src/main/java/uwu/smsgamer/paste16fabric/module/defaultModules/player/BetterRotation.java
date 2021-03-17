package uwu.smsgamer.paste16fabric.module.defaultModules.player;

import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.values.*;

public class BetterRotation extends PasteModule {
    private static BetterRotation instance;

    public VBool noPitchClamp = new VBool(this, "NoPitchClamp", false, "Don't clamp pitch.");
    public VBool implementRoll = new VBool(this, "ImplementRoll", false, "Implements roll. Doesn't work. :/");
    public VNumber test = new VNumber(this, "Test", 0, 0, 7, 1, "Test.");
    public VNumber test1 = new VNumber(this, "Test1", 0, 0, 5, 1, "Test 1.");

    public static BetterRotation getInstance() {
        if (instance == null) new BetterRotation();
        return instance;
    }

    private BetterRotation() {
        super("BetterRotation", "Better rotations for the client.", ModuleCategory.PLAYER);
        instance = this;
    }
}
