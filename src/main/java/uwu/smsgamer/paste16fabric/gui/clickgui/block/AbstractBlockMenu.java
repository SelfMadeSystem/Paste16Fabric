package uwu.smsgamer.paste16fabric.gui.clickgui.block;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.*;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

import java.util.List;

public abstract class AbstractBlockMenu extends Screen implements MinecraftHelper {
    public BlockClickGui gui;

    public float posX = 50;
    public float posY = 50;

    protected AbstractBlockMenu(BlockClickGui gui) {
        super(NarratorManager.EMPTY);
        this.gui = gui;
    }

    @Override
    public abstract List<? extends AbstractClickComponent> children();

    public abstract String getName();

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        Vec2f size = getSize(gui);
        final float w = size.x;
        final float h = size.y;
        float x = posX;
        float y = posY;
        fill(matrices, (int) x, (int) y - 10, (int) (x + w), (int) y, 0xFFFF0000);
        drawCenteredString(matrices, mc.textRenderer, getName(), (int) (x + w / 2), (int) (y - 5 - mc.textRenderer.fontHeight / 2), 0xFF404040);
        fill(matrices, (int) x, (int) y, (int) (x + w), (int) (y + h), 0xFFFFFFFF);
        final float maxX = x + w - 2;
        float maxY = 0;
        for (AbstractClickComponent component : children()) {
            Vec2f componentSize = component.render(matrices, gui, x, y, w, h);
            x += componentSize.x;
            maxY = Math.max(maxY, componentSize.y);
            if (x >= maxX) {
                x = posX;
                y += maxY;
                maxY = 0;
            }
        }
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        Vec2f size = getSize(gui);
        return mouseX >= posX && mouseX <= posX + size.x &&
          mouseY >= posY - 10 && mouseY <= posY + size.y;
    }

    public boolean isMouseOverMove(double mouseX, double mouseY) {
        Vec2f size = getSize(gui);
        return mouseX >= posX && mouseX <= posX + size.x &&
          mouseY >= posY - 10 && mouseY <= posY;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOverMove(mouseX, mouseY) && button == 0) {
            setDragging(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (isDragging() && button == 0) {
            setDragging(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isDragging()) {
            posX += deltaX;
            posY += deltaY;
            return true;
        }
        return false;
    }

    public abstract Vec2f getSize(AbstractClickGui gui);
}
