package uwu.smsgamer.paste.paste16fabric.command;

import java.util.*;

public final class CommandManager {
    private static CommandManager instance;

    public static CommandManager getInstance() {
        if (instance == null) instance = new CommandManager();
        return instance;
    }

    public CommandSystem mainSystem = new CommandSystem(".");

    private final Set<ICommandSystem> commandSystems = new LinkedHashSet<>();

    public CommandManager() {
        addCommandSystem(mainSystem);
    }

    public void addCommandSystem(CommandSystem commandSystem) {
        commandSystems.add(commandSystem);
    }

    public boolean handleCommand(String command) {
        for (ICommandSystem commandSystem : commandSystems)
            if (commandSystem.canRunCommand(command)) {
                commandSystem.runCommand(command);
                return true;
            }
        return false;
    }

    public List<String> handleTabComplete(String command) {
        for (ICommandSystem commandSystem : commandSystems)
            if (commandSystem.canRunCommand(command))
                return commandSystem.tabComplete(command);
        return null;
    }
}
