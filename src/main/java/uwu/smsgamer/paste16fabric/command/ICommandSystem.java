package uwu.smsgamer.paste16fabric.command;

import java.util.List;

public interface ICommandSystem {
    boolean canRunCommand(String command);

    void runCommand(String command);

    List<String> tabComplete(String command);
}
