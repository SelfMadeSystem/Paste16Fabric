package uwu.smsgamer.paste16fabric.command;

import com.mojang.brigadier.suggestion.Suggestions;

public interface ICommand {
    boolean canRunCommand(String label);

    void runCommand(String label, String[] args);

    Suggestions tabComplete(String label, String[] args);

    default void init() {
    }
}
