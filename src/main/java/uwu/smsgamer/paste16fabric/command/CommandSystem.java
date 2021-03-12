package uwu.smsgamer.paste16fabric.command;

import java.util.*;

public class CommandSystem implements ICommandSystem {
    protected String prefix;
    protected Set<ICommand> commands = new HashSet<>();

    public CommandSystem(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean canRunCommand(String command) {
        return command.startsWith(prefix);
    }

    @Override
    public void runCommand(String command) {
        String str = command.substring(prefix.length());
        String[] split = str.split(" ");
        String label = split[0];
        String[] args = Arrays.copyOfRange(split, 1, split.length);
        for (ICommand iCommand : commands) {
            if (iCommand.canRunCommand(label)) {
                iCommand.runCommand(label, args);
                return;
            }
        }
        System.out.println("Unknown command: " + label);
    }

    @Override
    public List<String> tabComplete(String command) {
        String str = command.substring(prefix.length());
        String[] split = str.split(" ");
        String label = split[0];
        String[] args = Arrays.copyOfRange(split, 1, split.length);
        for (ICommand iCommand : commands) {
            if (iCommand.canRunCommand(label)) {
                return iCommand.tabComplete(label, args);
            }
        }
        return null;
    }

    @Override
    public void init() {
        for (ICommand iCommand : commands) iCommand.init();
    }
}
