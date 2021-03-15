package uwu.smsgamer.paste16fabric.gui.clickgui;

public class ClickGuiManager {
    private static ClickGuiManager instance;

    public static ClickGuiManager getInstance() {
        if (instance == null) instance = new ClickGuiManager();
        return instance;
    }


}
