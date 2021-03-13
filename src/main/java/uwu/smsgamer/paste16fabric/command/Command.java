package uwu.smsgamer.paste16fabric.command;

import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;

import java.util.*;

public abstract class Command implements ICommand, MinecraftHelper {
    public List<String> aliases;

    public Command(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    @Override
    public boolean canRunCommand(String label) {
        return aliases.contains(label);
    }
}
