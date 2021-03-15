package uwu.smsgamer.paste16fabric.gui.clickgui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import uwu.smsgamer.paste16fabric.module.defaultmodules.render.ClickGui;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

public class AbstractClickGui extends Screen implements MinecraftHelper {
    protected AbstractClickGui() {
        super(NarratorManager.EMPTY);
    }

    @Override
    public void onClose() {
        this.client.openScreen(null);
        ClickGui.getInstance().setState(false);
    }
}
