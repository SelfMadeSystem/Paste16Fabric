package uwu.smsgamer.paste16fabric.gui.hud;

import uwu.smsgamer.paste16fabric.config.ConfigValue;
import uwu.smsgamer.paste16fabric.events.events.*;
import uwu.smsgamer.paste16fabric.gui.hud.components.TextComponent;
import uwu.smsgamer.paste16fabric.gui.hud.components.tabgui.TabGui;
import uwu.smsgamer.paste16fabric.utils.Colours;

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

        tabgui.setYOffset(30);
        tabgui.setHorizontalAlignment(-1);
        tabgui.setVerticalAlignment(-1);

        list.add(tabgui);

        TextComponent text = new TextComponent();

        text.setText("PasteClient");
        text.setSize(3);
        text.setHorizontalAlignment(-1);
        text.setVerticalAlignment(-1);
        text.setYOffset(2);
        text.setXOffset(2);
        text.setColour(Colours.CYAN);

        list.add(text);

        components = new ConfigValue<>("hud.components", list);
    }

    public ConfigValue<List<HudComponent>> components;

    public void render(RenderEvent event) {
        for (HudComponent component : components.getValue()) component.onRender(event.matrices, event.partialTicks);
    }

    public void onKey(KeyPressEvent event) {
        for (HudComponent hudComponent : components.getValue()) hudComponent.onKey(event);
    }

    public void init() {
        for (HudComponent hudComponent : components.getValue()) hudComponent.init();
    }
}
