package uwu.smsgamer.paste16fabric.gui.clickgui;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import uwu.smsgamer.paste16fabric.module.defaultModules.render.ClickGui;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;
import uwu.smsgamer.paste16fabric.values.Val;

import java.util.List;

// Todo: Descriptions & internal name (for commands)
public abstract class AbstractClickGui extends Screen implements MinecraftHelper {
    public Element override;

    protected AbstractClickGui() {
        super(NarratorManager.EMPTY);
    }

    @Override
    public void onClose() {
        this.client.openScreen(null);
        ClickGui.getInstance().setState(false);
    }

    public abstract void openValues(List<Val<?>> vals);

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (override != null) return override.mouseClicked(mouseX, mouseY, button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (override != null) return override.mouseReleased(mouseX, mouseY, button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (override != null) return override.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (override != null) return override.mouseScrolled(mouseX, mouseY, amount);
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (override != null) return override.keyPressed(keyCode, scanCode, modifiers);
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (override != null) return override.keyReleased(keyCode, scanCode, modifiers);
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    public boolean charTyped(char chr, int modifiers) {
        if (override != null) return override.charTyped(chr, modifiers);
        return super.charTyped(chr, modifiers);
    }
}
