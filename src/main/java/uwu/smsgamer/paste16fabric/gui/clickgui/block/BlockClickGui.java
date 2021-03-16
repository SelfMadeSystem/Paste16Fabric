package uwu.smsgamer.paste16fabric.gui.clickgui.block;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.gui.clickgui.block.menus.CategoryMenu;
import uwu.smsgamer.paste16fabric.values.Val;

import java.util.*;

public class BlockClickGui extends AbstractClickGui {
    public final CategoryMenu categoryMenu;
    public final List<AbstractBlockMenu> menus = new LinkedList<>();

    public BlockClickGui() {
        categoryMenu = new CategoryMenu(this);
        menus.add(categoryMenu);
    }

    @Override
    public void openValues(List<Val<?>> vals) {

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
        for (int i = menus.size() - 1; i >= 0; i--) {
            if(menus.get(i).mouseClicked(mouseX, mouseY, button)) return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (int i = menus.size() - 1; i >= 0; i--) {
            if(menus.get(i).mouseReleased(mouseX, mouseY, button)) return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for (int i = menus.size() - 1; i >= 0; i--) {
            if(menus.get(i).mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) return true;
        }
        return false;
    }
}
