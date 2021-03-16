package uwu.smsgamer.paste16fabric.module.defaultModules.render;

import uwu.smsgamer.paste16fabric.gui.clickgui.ClickGuiManager;
import uwu.smsgamer.paste16fabric.module.*;

public class ClickGui extends PasteModule {
    private static ClickGui instance;

    public static ClickGui getInstance() {
        if (instance == null) new ClickGui();
        return instance;
    }

    private ClickGui() {
        super("ClickGui", "ClickGui thing", ModuleCategory.RENDER, false, 344);
        instance = this;
    }

    @Override
    protected void onEnable() {
        ClickGuiManager.getInstance().openClickGUI();
//        setState(false);
    }
}
