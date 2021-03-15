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
}

class CategoryBlock extends AbstractClickComponent {
    private final ModuleCategory value;
    private final CategoryMenu menu;

    public CategoryBlock(ModuleCategory value, CategoryMenu menu) {
        this.value = value;
        this.menu = menu;
    }

    @Override
    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x + 1, (int) y + 1, (int) (x + width) - 2, (int) (y + height) - 2, 0xFF777777);
        drawCenteredString(stack, mc.textRenderer, value.getName(), (int) (x + width / 2), (int) (y + height / 2 - mc.textRenderer.fontHeight / 2), 0xFF404040);
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
}
