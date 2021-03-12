package uwu.smsgamer.paste.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste.paste16fabric.module.*;
import uwu.smsgamer.paste.paste16fabric.utils.Render2D;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TabCategory extends TabBlock {
    public final ModuleCategory category;
    public List<TabModule> modules;

    public TabCategory(ModuleCategory category) {
        this.category = category;
        this.modules = ModuleManager.getInstance().getModulesByCategory(category).stream().map(TabModule::new).collect(Collectors.toList());
    }

    @Override
    public float render(MatrixStack matrices, float x, float y) {
        Render2D.drawBorderedRect(matrices.peek().getModel(), x, y, x + WIDTH, y + HEIGHT, BORDER, bg, fg);

        Render2D.drawString(matrices.peek().getModel(), category.getName(), x + BORDER + 2, y + BORDER + 1, true, false, Color.BLACK);

        return y + HEIGHT - BORDER;
    }
}
