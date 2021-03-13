package uwu.smsgamer.paste16fabric.command;

import com.mojang.brigadier.suggestion.Suggestions;

public interface ICommandSystem {
    boolean canRunCommand(String command);

    void runCommand(String command);

    Suggestions tabComplete(String command);

    default void init(){}
}
