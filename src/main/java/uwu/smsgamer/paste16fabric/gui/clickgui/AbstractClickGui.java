package uwu.smsgamer.paste16fabric.gui.clickgui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

public class AbstractClickGui extends Screen implements MinecraftHelper {
    protected AbstractClickGui(String guiName) {
        super(new LiteralText(guiName));
    }
}
