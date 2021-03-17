package uwu.smsgamer.paste16fabric.gui.clickgui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import uwu.smsgamer.paste16fabric.module.defaultModules.render.ClickGui;
import uwu.smsgamer.paste16fabric.utils.*;
import uwu.smsgamer.paste16fabric.values.Val;

import java.util.List;

// Todo: openValues
public abstract class AbstractClickGui extends Screen implements MinecraftHelper {
    public AbstractClickComponent override;
    public String description;
    public String internName;

    protected AbstractClickGui() {
        super(NarratorManager.EMPTY);
    }

    @Override
    public void onClose() {
        this.client.openScreen(null);
        ClickGui.getInstance().setState(false);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        drawDescriptions(matrices);
        if (override != null) override.postRender(matrices);
    }

    public void drawDescriptions(MatrixStack matrices) {
        int width = mc.getWindow().getScaledWidth();
        int height = mc.getWindow().getScaledHeight();

        int fontHeight = mc.textRenderer.fontHeight;

        Matrix4f matrix = matrices.peek().getModel();

        if (description != null && !description.isBlank()) {
            Render2D.drawBorderedRect(matrix, 0, height - fontHeight - 2, mc.textRenderer.getWidth(description) + 3, height, 1, Colours.WHITE, Colours.BLACK);
            Render2D.drawString(matrix, description, 2, height - fontHeight / 2F, -1, 0, false, false, Colours.BLACK);
        }

        if (internName != null && !internName.isBlank()) {
            Render2D.drawBorderedRect(matrix, width - mc.textRenderer.getWidth(internName) - 3, height - fontHeight - 2,  width, height, 1, Colours.WHITE, Colours.BLACK);
            Render2D.drawString(matrix, internName, width - mc.textRenderer.getWidth(internName) - 1, height - fontHeight / 2F, -1, 0, false, false, Colours.BLACK);
        }
    }

    public abstract void openValues(List<Val<?>> vals);

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (override != null) {
            override.mouseClicked(mouseX, mouseY, button);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (override != null) {
            override.mouseReleased(mouseX, mouseY, button);
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (override != null) {
            override.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (override != null) {
            override.mouseScrolled(mouseX, mouseY, amount);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (override != null) {
            override.keyPressed(keyCode, scanCode, modifiers);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (override != null) {
            override.keyReleased(keyCode, scanCode, modifiers);
            return true;
        }
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    public boolean charTyped(char chr, int modifiers) {
        if (override != null) {
            override.charTyped(chr, modifiers);
            return true;
        }
        return super.charTyped(chr, modifiers);
    }
}
