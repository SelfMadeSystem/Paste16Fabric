package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.utils.Render2D;

import java.util.List;
import java.util.stream.Collectors;

public class TabCategory extends TabBlock {
    public final ModuleCategory category;
    public List<TabModule> modules;

    public TabCategory(TabGui gui, ModuleCategory category) {
        super(gui);
        this.category = category;
        this.modules = ModuleManager.getInstance().getModulesByCategory(category).stream().map(m -> new TabModule(gui, m)).collect(Collectors.toList());
    }

    @Override
    public void render(MatrixStack matrices, float x, float y) {
        Render2D.drawBorderedRect(matrices.peek().getModel(), x, y, x + WIDTH, y + HEIGHT, BORDER, gui.getOptions().getBoxInside(), gui.getOptions().getBoxBorder());

        int horizontalAlignment = gui.getHorizontalAlignment();
        int verticalAlignment = gui.getVerticalAlignment();

        switch (horizontalAlignment) {
            case -1:
                x += BORDER + 2;
                break;
            case 0:
                x += WIDTH * 0.5;
                break;
            case 1:
                x += WIDTH - BORDER - 2;
                break;
        }

        switch (verticalAlignment) {
            case -1:
                y += BORDER + 1;
                break;
            case 0:
                y += HEIGHT * 0.5;
                break;
            case 1:
                y += HEIGHT - BORDER - 1;
                break;
        }



        Render2D.drawString(matrices.peek().getModel(), category.getName(), x, y,
          gui.getOptions().getTextHorizontalAlignment(), gui.getOptions().getTextVerticalAlignment(),
          true, false, gui.getOptions().getTextColour());
    }
}
