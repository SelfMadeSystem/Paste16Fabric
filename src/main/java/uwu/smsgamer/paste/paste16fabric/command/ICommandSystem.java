package uwu.smsgamer.paste.core.command;

import java.util.List;

public interface ICommandSystem {
    boolean canRunCommand(String command);

    void runCommand(String command);

    List<String> tabComplete(String command);
}
