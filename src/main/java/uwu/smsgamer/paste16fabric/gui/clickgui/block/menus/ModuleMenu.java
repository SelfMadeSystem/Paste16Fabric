package uwu.smsgamer.paste16fabric.gui.clickgui.block.menus;

import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.*;
import uwu.smsgamer.paste16fabric.gui.clickgui.block.*;
import uwu.smsgamer.paste16fabric.module.*;

import java.util.*;

public class ModuleMenu extends AbstractBlockMenu {
    private final List<ModuleBlock> children = new LinkedList<>();
    public final ModuleCategory category;

    public ModuleMenu(BlockClickGui gui, ModuleCategory category) {
        super(gui, 200, 40);
        this.category = category;
        for (PasteModule pasteModule : ModuleManager.getInstance().getModulesByCategory(category)) {
            children.add(new ModuleBlock(pasteModule, this));
        }
        this.minWidth = 60;
    }

    @Override
    public List<? extends AbstractClickComponent> children() {
        return children;
    }

    @Override
    public String getName() {
        return category.getName();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) return true;
        if (button <= 1 && isMouseOver(mouseX, mouseY)) {
            for (ModuleBlock child : children) {
                if (child.mouseClicked(mouseX, mouseY, button)) return true;
            }
        }
        return false;
    }
}

class ModuleBlock extends AbstractClickComponent {
    private final PasteModule module;
    private final ModuleMenu menu;

    public ModuleBlock(PasteModule module, ModuleMenu menu) {
        this.module = module;
        this.menu = menu;
    }

    @Override
    public Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        fill(stack, (int) x + 1, (int) y + 1, (int) (x + width) - 2, (int) (y + height) - 2, 0xFF777777);
        drawCenteredString(stack, mc.textRenderer, module.getName(), (int) (x + width / 2), (int) (y + height / 2 - mc.textRenderer.fontHeight / 2), 0xFF404040);
        if (isMouseOver(mouseX, mouseY) && (gui.override == null || gui.override == this)) {
            gui.description = module.getDescription();
        }
        return new Vec2f(width, height);
    }

    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }

    @Override
    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        double max = Math.max(1, Math.floor(w / 55));
        w /= Math.min(menu.children().size(), max);
        h = 20;
//        menu.minHeight = (float) (h * Math.ceil(menu.children().size() / max));
        return new Vec2f(w, h);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            menu.gui.menus.add(new ValueMenu(menu.gui, module));
            return true;
        }
        return false;
    }
}