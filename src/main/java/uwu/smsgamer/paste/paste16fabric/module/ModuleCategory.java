package uwu.smsgamer.paste.paste16fabric.module;

import lombok.Getter;

public enum ModuleCategory {
    COMBAT("Combat"),
    MISC("Misc"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    WORLD("World");
    @Getter
    final String name;


    ModuleCategory(String name) {
        this.name = name;
    }
}
