package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.utils.Render2D;

import java.util.*;
import java.util.stream.Collectors;

public class TabCategory extends TabBlock {
    public final ModuleCategory category;
    public List<TabModule> modules;

    public TabCategory(TabGui gui, ModuleCategory category) {
        super(gui);
        this.category = category;
        this.modules = ModuleManager.getInstance().getModulesByCategory(category).stream().map(m -> new TabModule(gui, m)).collect(Collectors.toList());
        this.modules.sort(Comparator.comparing(a -> a.module.getName()));
    }

    @Override
    public void render(MatrixStack matrices, float x, float y) {
        Matrix4f model = Render2D.identity();//matrices.peek().getModel();
        Render2D.drawBorderedRect(model, x, y, x + opt().getBoxWidth(), y + opt().getBoxHeight(), opt().getBoxBorder(),
          opt().getBoxInsideColour(), opt().getBoxBorderColour());

        int horizontalAlignment = opt().getTextHorizontalAlignment();
        int verticalAlignment = opt().getTextVerticalAlignment();

        switch (horizontalAlignment) {
            case -1:
                x += opt().getBoxBorder() + 2;
                break;
            case 0:
                x += opt().getBoxWidth() * 0.5;
                break;
            case 1:
                x += opt().getBoxWidth() - opt().getBoxBorder() - 2;
                break;
        }

        switch (verticalAlignment) {
            case -1:
                y += opt().getBoxBorder() + 1;
                break;
            case 0:
                y += opt().getBoxHeight() * 0.5;
                break;
            case 1:
                y += opt().getBoxHeight() - opt().getBoxBorder() - 1;
                break;
        }

        model.multiply((float) opt().getTextSize());

        x /= opt().getTextSize();
        y /= opt().getTextSize();

        Render2D.drawString(model, category.getName(), x, y,
          opt().getTextHorizontalAlignment(), opt().getTextVerticalAlignment(),
          true, false, opt().getTextColour());
    }
}
