package uwu.smsgamer.paste16fabric.gui.clickgui.block.menus;

import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.*;
import uwu.smsgamer.paste16fabric.gui.clickgui.block.*;
import uwu.smsgamer.paste16fabric.module.ModuleCategory;

import java.util.*;

public class CategoryMenu extends AbstractBlockMenu {
    private final List<CategoryBlock> children = new LinkedList<>();

    public CategoryMenu(BlockClickGui gui) {
        super(gui, 200, 40, false);
        for (ModuleCategory value : ModuleCategory.values()) {
            children.add(new CategoryBlock(value, this));
        }
        this.minWidth = 60;
    }

    @Override
    public List<? extends AbstractClickComponent> children() {
        return children;
    }

    @Override
    public String getName() {
        return "Categories";
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) return true;
        if (button == 0 && isMouseOver(mouseX, mouseY)) {
            for (CategoryBlock child : children) {
                if (child.mouseClicked(mouseX, mouseY, button)) return true;
            }
        }
        return false;
    }
}

class CategoryBlock extends AbstractClickComponent {
    private final ModuleCategory category;
    private final CategoryMenu menu;

    public CategoryBlock(ModuleCategory category, CategoryMenu menu) {
        this.category = category;
        this.menu = menu;
    }

    @Override
    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x + 1, (int) y + 1, (int) (x + width) - 2, (int) (y + height) - 2, 0xFF777777);
        drawCenteredString(stack, mc.textRenderer, category.getName(), (int) (x + width / 2), (int) (y + height / 2 - mc.textRenderer.fontHeight / 2), 0xFF404040);
        return new Vec2f(width, height);
    }

    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }

    @Override
    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        double max = Math.max(1, Math.floor(w / 55));
        w /= Math.min(ModuleCategory.values().length, max);
        h = 20;
        menu.minHeight = (float) (h * Math.ceil(ModuleCategory.values().length / max));
        return super.getSize(gui, w, h);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            if (menu.gui.menus.stream().noneMatch(menu ->
              menu instanceof ModuleMenu && ((ModuleMenu) menu).category == category))
                menu.gui.menus.add(new ModuleMenu(menu.gui, category));
            return true;
        }
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= lastX && mouseX <= lastX + width &&
          mouseY >= lastY && mouseY <= lastY + height;
    }
}
