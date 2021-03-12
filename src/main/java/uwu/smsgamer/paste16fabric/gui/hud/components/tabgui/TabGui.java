package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste16fabric.module.ModuleCategory;
import uwu.smsgamer.paste16fabric.utils.*;

import java.lang.reflect.*;
import java.util.*;

public class TabGui extends HudComponent {
    private final List<TabBlock> modules = new ArrayList<>();
    @Getter
    @Setter
    private SelectedOptions categoryOptions = new SelectedOptions();
    @Getter
    @Setter
    private SelectedOptions moduleOptions = new SelectedOptions();
    @Getter
    @Setter
    private String categoryClassName = TabCategory.class.getName();
    @Getter
    @Setter
    private String moduleClassName = TabModule.class.getName();

    @Override
    public void init() {
        Class<? extends TabBlock> clazz = TabCategory.class;
        Constructor<? extends TabBlock> constructor = null;
        try {
            constructor = clazz.getConstructor(TabGui.class, ModuleCategory.class);
            Class<?> cl =  Class.forName(categoryClassName);
            Constructor<?> co = cl.getConstructor(TabGui.class, ModuleCategory.class);
            constructor = (Constructor<? extends TabBlock>) co;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (constructor == null) {
            System.err.println("Unable to get category constructor!");
            return;
        }
        for (ModuleCategory category : ModuleCategory.values()) {
            try {
                this.modules.add(constructor.newInstance(this, category));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.modules.get(0).hovered = true;
    }

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
        float x = getX();
        float top = getY();
        float y = top;
        float yAdd = categoryOptions.baseOptions.boxHeight - categoryOptions.baseOptions.boxBorder;
        float ySel = categoryOptions.selectedOptions.boxHeight - categoryOptions.selectedOptions.boxBorder;
        float yHov = categoryOptions.hoverOptions.boxHeight - categoryOptions.hoverOptions.boxBorder;
        if (verticalAlignment == 1)
            y -= getHeight();
        else if (verticalAlignment == 0) {
            y -= getHeight() * 0.5;
        }
        if (horizontalAlignment == 1)
            x -= getWidth();
        else if (horizontalAlignment == 0) {
            x -= getWidth() * 0.5;
        }
        for (TabBlock category : modules) category.preRender(matrices, x, y, top);
        for (TabBlock category : modules) {
            category.render(matrices, x, y, top);
            y += category.selected ? ySel :
              category.hovered ? yHov : yAdd;
        }
    }

    private int hovered = 0;
    @Setter
    @Getter
    private TabBlock current;

    @Override
    public void onKey(KeyPressEvent event) {
        if (current != null) {
            current.onKey(event);
            return;
        }
        if (event.pressType > 0) {
            int prevH = hovered;
            switch(event.key) {
                case 265: // Up
                    hovered--;
                    if (hovered < 0) hovered = event.pressType == 2 ? 0 : modules.size() - 1;
                    break;
                case 264: // Down
                    hovered++;
                    if (hovered >= modules.size()) hovered = event.pressType == 2 ? modules.size() - 1 : 0;
                    break;
                case 262: // Right
                    current = modules.get(hovered);
                    current.setSelected(true);
                    current.select(this, null);
                    break;
            }
            if (prevH != hovered) {
                modules.get(prevH).setHovered(false);
                modules.get(hovered).setHovered(true);
            }
        }
    }

    @Override
    public float getWidth() {
        return Math.max(categoryOptions.baseOptions.boxWidth, categoryOptions.hoverOptions.boxWidth);
    }

    @Override
    public float getHeight() {
        return categoryOptions.baseOptions.boxHeight * (modules.size() - 1) + categoryOptions.hoverOptions.boxHeight;
    }

    @Getter
    @Setter
    public static class SelectedOptions {
        private Options baseOptions;
        private Options hoverOptions;
        private Options selectedOptions;
        private int textHorizontalAlignment = -1;
        private int textVerticalAlignment = 0;
        private int fade = 100;

        public SelectedOptions() {
            baseOptions = new Options();
            hoverOptions = new Options();
            hoverOptions.setBoxBorder(3);
            hoverOptions.setBoxInsideColour(Colours.LIGHT_GRAY);
            selectedOptions = new Options();
            selectedOptions.setBoxBorder(4);
            hoverOptions.setBoxInsideColour(Colours.GRAY);
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
        private int yOffset = 0;
        private int xOffset = 0;
    }
}
