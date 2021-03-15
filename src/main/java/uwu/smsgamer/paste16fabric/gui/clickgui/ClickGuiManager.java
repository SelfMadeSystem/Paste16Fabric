package uwu.smsgamer.paste16fabric.gui.clickgui;

import uwu.smsgamer.paste16fabric.gui.clickgui.block.BlockClickGui;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

public class ClickGuiManager implements MinecraftHelper {
    private static ClickGuiManager instance;

    public static ClickGuiManager getInstance() {
        if (instance == null) instance = new ClickGuiManager();
        return instance;
    }

    public void openClickGUI() {
        mc.openScreen(new BlockClickGui());
    }
}
