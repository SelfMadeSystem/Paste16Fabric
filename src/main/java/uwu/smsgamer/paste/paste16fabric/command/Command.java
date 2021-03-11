package uwu.smsgamer.paste.core.command;

import java.util.*;

public abstract class Command implements ICommand {
    public List<String> aliases;

    public Command(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    @Override
    public boolean canRunCommand(String label) {
        return aliases.contains(label);
    }
}
