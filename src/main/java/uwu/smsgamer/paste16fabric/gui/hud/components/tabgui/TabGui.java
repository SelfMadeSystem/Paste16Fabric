package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste16fabric.module.ModuleCategory;
import uwu.smsgamer.paste16fabric.utils.*;

import java.util.*;

public class TabGui extends HudComponent {
    private final List<TabCategory> modules = new ArrayList<>();
    @Getter
    @Setter
    private Options options = new Options();

    public TabGui() {
        for (ModuleCategory category : ModuleCategory.values()) {
            modules.add(new TabCategory(this, category));
        }
        this.modules.sort(Comparator.comparing(a -> a.category.getName()));
    }

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
        float x = getX();
        float y = getY();
        float yAdd = options.boxHeight - options.boxBorder;
        if (verticalAlignment == 1)
            y -= yAdd * modules.size();
        else if (verticalAlignment == 0) {
            y -= yAdd * modules.size() * 0.5;
        }
        if (horizontalAlignment == 1)
            x -= options.boxWidth;
        else if (horizontalAlignment == 0) {
            x -= options.boxWidth * 0.5;
        }
        for (TabCategory category : modules) {
            category.render(matrices, x, y);
            y += yAdd;
        }
    }

    @Override
    public void onKey(KeyPressEvent event) {
        if (event.pressType > 0) {
            switch(event.key) {
                case 265: // Up
                    break;
                case 264: // Down
                    break;
                case 263: // Left
                    break;
                case 262: // Right
                    break;
            }
        }
    }

    @Getter
    @Setter
    public static class Options {
        private Colour boxInsideColour = Colours.WHITE;
        private Colour boxBorderColour = Colours.RED;
        private Colour textColour = Colours.BLACK;
        private int boxWidth = 100;
        private int boxHeight = 20;
        private int boxBorder = 2;
        private double textSize = 1;
        private int textHorizontalAlignment = -1;
        private int textVerticalAlignment = 0;
    }
}
