package uwu.smsgamer.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import uwu.smsgamer.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.module.defaultmodules.render.Hud;
import uwu.smsgamer.paste16fabric.utils.fontRenderer.GlyphPageFontRenderer;

import java.lang.reflect.*;
import java.util.*;

public class TabCategory extends TabBlock {
    public final ModuleCategory category;
    public final List<TabBlock> modules;

    public TabCategory(TabGui gui, ModuleCategory category) {
        super(gui);

        this.category = category;
        this.modules = new ArrayList<>();

        Class<? extends TabBlock> clazz = TabModule.class;
        Constructor<? extends TabBlock> constructor = null;
        try {
            constructor = clazz.getConstructor(TabGui.class, PasteModule.class);
            Class<?> cl =  Class.forName(gui.getModuleClassName());
            Constructor<?> co = cl.getConstructor(TabGui.class, PasteModule.class);
            constructor = (Constructor<? extends TabBlock>) co;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (constructor == null) {
            System.err.println("Unable to get module constructor!");
            return;
        }

        for (PasteModule pasteModule : ModuleManager.getInstance().getModulesByCategory(category)) {
            if (pasteModule.getClass().equals(Hud.class)) continue;
            try {
                TabBlock block = constructor.newInstance(gui, pasteModule);
                this.modules.add(block);
                block.setSelected(pasteModule.getState());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (modules.size() > 0) modules.get(0).setHovered(true);
    }

    @Override
    protected TabGui.CategoryOptions getOptions() {
        return gui.getCategoryOptions();
    }

    @Override
    protected TabGui.Options getOpt() {
        TabGui.CategoryOptions o = getOptions();
        return selected ? o.getSelectedOptions() :
          hovered ? o.getHoverOptions() : o.getBaseOptions();
    }

    @Override
    protected String getText() {
        return category.getName();
    }

    @Override
    protected GlyphPageFontRenderer getRenderer() {
        return gui.categoryRenderer;
    }

    @Override
    public void preRender(MatrixStack matrices, float x, float y, float top) {
        if (this.selected) {
            y = top;
            x += getBoxWidth();
            x += getXOffset();
            y += getYOffset();
            for (TabBlock module : modules) {
                module.render(matrices, x, y, top);
                y += module.getBoxHeight();
            }
        }
    }

    @Override
    public void onKey(KeyPressEvent event) {
        if (current != null) {
            current.onKey(event);
            return;
        }
        if (event.pressType > 0) {
            int prevH = hover;
            switch (event.key) {
                case 265: // Up
                    hover--;
                    if (hover < 0) hover = event.pressType == 2 ? 0 : modules.size() - 1;
                    break;
                case 264: // Down
                    hover++;
                    if (hover >= modules.size()) hover = event.pressType == 2 ? modules.size() - 1 : 0;
                    break;
                case 263: // Left
                    setSelected(false);
                    gui.current = null;
                    hover = 0;
                    break;
                case 262: // Right
                    current = modules.get(hover);
                    current.setSelected(true);
                    current.select(null, this);
                    break;
            }
            if (prevH != hover) {
                modules.get(prevH).setHovered(false);
                modules.get(hover).setHovered(true);
            }
        }
    }

    @Override
    public void select(@Nullable TabGui gui, @Nullable TabBlock block) {
        if (gui != null && modules.size() == 0) {
            gui.current = null;
            this.setSelected(false);
        }
    }
}
