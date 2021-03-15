package uwu.smsgamer.paste16fabric.gui.clickgui.block;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.gui.clickgui.block.menus.CategoryMenu;

import java.util.*;

public class BlockClickGui extends AbstractClickGui {
    private final CategoryMenu categoryMenu;
    private final List<AbstractBlockMenu> menus = new LinkedList<>();

    public BlockClickGui() {
        categoryMenu = new CategoryMenu(this);
        menus.add(categoryMenu);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        for (AbstractBlockMenu menu : menus) {
            menu.render(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (AbstractBlockMenu menu : menus) {
            menu.mouseClicked(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (AbstractBlockMenu menu : menus) {
            menu.mouseReleased(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for (AbstractBlockMenu menu : menus) {
            menu.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
        return false;
    }
}
