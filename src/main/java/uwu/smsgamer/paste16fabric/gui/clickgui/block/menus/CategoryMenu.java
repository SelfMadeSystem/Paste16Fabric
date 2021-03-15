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
        super(gui);
        for (ModuleCategory value : ModuleCategory.values()) {
            children.add(new CategoryBlock(value));
        }
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
    public Vec2f getSize(AbstractClickGui gui) {
        return new Vec2f(200, 40);
    }
}

class CategoryBlock extends AbstractClickComponent {
    private final ModuleCategory value;

    public CategoryBlock(ModuleCategory value) {
        this.value = value;
    }

    @Override
    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y) {
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
        w /= Math.max(1, Math.floor(w / 55));
        h = Math.min(h, 20);
        return super.getSize(gui, w, h);
    }
}
