package uwu.smsgamer.paste.paste16fabric.gui.hud.components.tabgui;

import net.minecraft.client.util.math.MatrixStack;
import uwu.smsgamer.paste.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste.paste16fabric.module.ModuleCategory;

import java.util.*;

public class TabGui extends HudComponent {
    private final Set<TabCategory> modules = new HashSet<>();

    public TabGui() {
        for (ModuleCategory category : ModuleCategory.values()) {
            modules.add(new TabCategory(category));
        }
    }

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
        float x = getX();
        float y = getY();
        for (TabCategory category : modules) {
            y = category.render(matrices, x, y);
        }
    }
}
