package uwu.smsgamer.paste16fabric.gui.hud;

import uwu.smsgamer.paste16fabric.config.ConfigValue;
import uwu.smsgamer.paste16fabric.events.events.RenderEvent;
import uwu.smsgamer.paste16fabric.gui.hud.components.tabgui.TabGui;

import java.util.*;

public class HudManager {
    private static HudManager instance;

    public static HudManager getInstance() {
        if (instance == null) instance = new HudManager();
        return instance;
    }

    public HudManager() {
        instance = this;

        List<HudComponent> list = new ArrayList<>();
        TabGui tabgui = new TabGui();

        tabgui.horizontalAlignment = -1;
        tabgui.verticalAlignment = -1;

        list.add(tabgui);

        components = new ConfigValue<>("hud.components", list);
    }

    public ConfigValue<List<HudComponent>> components;

    public void render(RenderEvent event) {
        for (HudComponent hudComponent : components.getValue()) {
            hudComponent.onRender(event.matrices, event.partialTicks);
        }
    }
}
