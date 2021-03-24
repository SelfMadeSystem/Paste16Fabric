package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste16fabric.gui.hud.components.HudText;
import uwu.smsgamer.paste16fabric.module.ModuleCategory;
import uwu.smsgamer.paste16fabric.utils.*;
import uwu.smsgamer.paste16fabric.utils.text.GlyphPageFontRenderer;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.List;

public class TabGui extends HudComponent {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final List<TabBlock> modules = new ArrayList<>();
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected GlyphPageFontRenderer categoryRenderer;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected GlyphPageFontRenderer moduleRenderer;
    @Getter
    @Setter
    private CategoryOptions categoryOptions = new CategoryOptions();
    @Getter
    @Setter
    private ModuleOptions moduleOptions = new ModuleOptions();
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
            Class<?> cl = Class.forName(categoryClassName);
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
        regenerateRenderers();
    }

    public void regenerateRenderers() {
        if (categoryOptions.getFontSettings().getFont() != null && !categoryOptions.getFontSettings().getFont().isEmpty())
            categoryRenderer = categoryOptions.getFontSettings().generateRenderer();

        if (moduleOptions.getFontSettings().getFont() != null && !moduleOptions.getFontSettings().getFont().isEmpty())
            moduleRenderer = moduleOptions.getFontSettings().generateRenderer();
    }

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
        float x = getX();
        float top = getY();
        float y = top;
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
            y += category.getBoxHeight();
        }
    }

    private int hovered = 0;
    protected TabBlock current;

    @Override
    public void onKey(KeyPressEvent event) {
        if (current != null) {
            current.onKey(event);
            return;
        }
        if (event.pressType > 0) {
            int prevH = hovered;
            switch (event.key) {
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
    public static class CategoryOptions {
        protected Options baseOptions;
        protected Options hoverOptions;
        protected Options selectedOptions;
        protected int textHorizontalAlignment = -1;
        protected int textVerticalAlignment = 0;
        protected HudText.FontSettings fontSettings = new HudText.FontSettings();
        protected int fade = 100;

        public CategoryOptions() {
            baseOptions = new Options();
            hoverOptions = new Options();
            hoverOptions.setBoxBorder(3);
            selectedOptions = new Options();
            selectedOptions.setBoxBorder(3);
        }
    }

    @Getter
    @Setter
    public static class ModuleOptions extends CategoryOptions {
        protected Options selectedHoverOptions;

        public ModuleOptions() {
            baseOptions = new Options();
            baseOptions.setBoxWidth(90);
            baseOptions.setBoxHeight(17);
            baseOptions.setTextSize(0.9);

            hoverOptions = baseOptions.clone();
            hoverOptions.setBoxBorder(2);

            selectedOptions = baseOptions.clone();
            selectedOptions.setTextColour(Colours.RED);

            selectedHoverOptions = baseOptions.clone();
            selectedHoverOptions.setBoxBorder(2);
            selectedHoverOptions.setTextColour(Colours.RED);
        }
    }

    @Getter
    @Setter
    public static class Options implements Cloneable {
        private Colour boxInsideColour = new RGB(0, 0, 0, 0.8);
        private Colour boxBorderColour = new RGB(0, 0, 0, 0.8);
        private Colour textColour = Colours.WHITE;
        private int boxWidth = 100;
        private int boxHeight = 20;
        private int boxBorder = 1;
        private double textSize = 1;
        private int yOffset = 0;
        private int xOffset = 0;

        @Override
        public Options clone() {
            try {
                return (Options) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
