package uwu.smsgamer.paste16fabric.command;

import java.util.List;

public interface ICommand {
    boolean canRunCommand(String label);

    void runCommand(String label, String[] args);

    List<String> tabComplete(String label, String[] args);

    default void init() {
    }
}
