package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste16fabric.module.ModuleCategory;
import uwu.smsgamer.paste16fabric.utils.*;

import java.util.*;

public class TabGui extends HudComponent {
    private final Set<TabCategory> modules = new HashSet<>();
    @Getter
    @Setter
    private Options options = new Options();

    public TabGui() {
        for (ModuleCategory category : ModuleCategory.values()) {
            modules.add(new TabCategory(this, category));
        }
    }

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
        float x = getX();
        float y = getY();
        float yAdd = TabBlock.HEIGHT - TabBlock.BORDER;
        if (verticalAlignment == 1)
            y -= yAdd * modules.size();
        else if (verticalAlignment == 0) {
            y -= yAdd * modules.size() * 0.5;
        }
        if (horizontalAlignment == 1)
            x -= TabBlock.WIDTH;
        else if (horizontalAlignment == 0) {
            x -= TabBlock.WIDTH * 0.5;
        }
        for (TabCategory category : modules) {
            category.render(matrices, x, y);
            y += yAdd;
        }
    }

    @Getter
    @Setter
    public static class Options {
        private Colour boxInside = Colours.WHITE;
        private Colour boxBorder = Colours.RED;
        private Colour textColour = Colours.BLACK;
        private int textHorizontalAlignment;
        private int textVerticalAlignment;
    }
}
