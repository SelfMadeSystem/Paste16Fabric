package uwu.smsgamer.paste16fabric.gui.clickgui.block;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickComponent;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

import java.util.List;

public abstract class AbstractBlockMenu extends Screen implements MinecraftHelper {
    public BlockClickGui gui;

    public float posX = 50;
    public float posY = 50;
    public float w;
    public float h;

    public float minHeight;
    public float minWidth = 50;

    public boolean resizing = false;
    public final boolean removable;

    protected AbstractBlockMenu(BlockClickGui gui, int w, int h) {
        this(gui, w, h, true);
    }

    protected AbstractBlockMenu(BlockClickGui gui, int w, int h, boolean removable) {
        super(NarratorManager.EMPTY);
        this.gui = gui;
        this.w = w;
        this.h = h;
        this.removable = removable;
    }

    @Override
    public abstract List<? extends AbstractClickComponent> children();

    public abstract String getName();

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        float x = posX;
        float y = posY;
        fill(matrices, (int) x, (int) y - 10, (int) (x + w), (int) y, 0xFFFF5500);
        if (removable) {
            fill(matrices, (int) (x + w - 10), (int) y - 10, (int) (x + w), (int) y, 0xFFFF0000);
        }
        drawCenteredString(matrices, mc.textRenderer, getName(), (int) (x + w / 2), (int) (y - 5 - mc.textRenderer.fontHeight / 2), 0xFF404040);
        fill(matrices, (int) x, (int) y, (int) (x + w), (int) (y + h), 0xFFFFFFFF);
        final float maxX = x + w - 2;
        float maxY = 0;
        for (AbstractClickComponent component : children()) {
            Vec2f componentSize = component.render(matrices, gui, x, y, mouseX, mouseY, w, h);
            x += componentSize.x;
            maxY = Math.max(maxY, componentSize.y);
            if (x >= maxX) {
                x = posX;
                y += maxY;
                maxY = 0;
            }
        }
        y += maxY;
        if (y - posY > h) h = minHeight = y - posY;

        fill(matrices, (int) (posX + w - 5), (int) (posY + h - 5), (int) (posX + w), (int) (posY + h), 0xFF00FF00);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= posX && mouseX <= posX + w &&
          mouseY >= posY - 10 && mouseY <= posY + h;
    }

    public boolean isMouseOverMove(double mouseX, double mouseY) {
        return mouseX >= posX && mouseX <= posX + w &&
          mouseY >= posY - 10 && mouseY <= posY;
    }

    public boolean isMouseOverResize(double mouseX, double mouseY) {
        return mouseX >= posX + w - 5 && mouseX <= posX + w &&
          mouseY >= posY + h - 5 && mouseY <= posY + h;
    }

    public boolean isMouseOverRemove(double mouseX, double mouseY) {
        return removable && mouseX >= posX + w - 10 && mouseX <= posX + w &&
          mouseY >= posY - 10 && mouseY <= posY;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            if (isMouseOverRemove(mouseX, mouseY)) {
                gui.menus.remove(this);
            } else if (isMouseOverMove(mouseX, mouseY)) {
                resizing = false;
                setDragging(true);
            } else if (isMouseOverResize(mouseX, mouseY)) {
                resizing = true;
                setDragging(true);
            }
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
            if (resizing) {
                w += deltaX;
                h += deltaY;
                w = Math.max(minWidth, w);
                h = Math.max(minHeight, h);
            } else {
                posX += deltaX;
                posY += deltaY;
            }
            return true;
        }
        return false;
    }
}
