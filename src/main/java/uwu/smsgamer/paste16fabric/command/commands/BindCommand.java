package uwu.smsgamer.paste16fabric.command.commands;

import com.mojang.brigadier.suggestion.Suggestions;
import net.minecraft.client.util.InputUtil;
import uwu.smsgamer.paste16fabric.command.Command;
import uwu.smsgamer.paste16fabric.events.*;
import uwu.smsgamer.paste16fabric.events.events.KeyPressEvent;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.utils.ChatUtils;

public class BindCommand extends Command {
    public BindCommand() {
        super("bind");
        EventManager.registerListener(this);
    }

    private PasteModule currentModule;

    @Override
    public void runCommand(String label, String[] args) {
        switch (args.length) {
            case 0:
                ChatUtils.sendMessage("&cHello! &#AA0077O&#AA0066w&#AA0055O&#AA0044 T" +
                  "&#AA0033h" +
                  "&#AA0022i" +
                  "&#AA0011s " +
                  "&#AA0000i" +
                  "&ls cool!&rReset.");
                break;
            case 1:
                PasteModule moduleByName = ModuleManager.getInstance().getModuleByName(args[0]);
                if (moduleByName != null){
                    ChatUtils.sendMessage(moduleByName.getName() + " is bound to: " + moduleByName.getKeyBind());
                }
        }
    }

    @Override
    public Suggestions tabComplete(String label, String[] args) {
        return null;
    }

    @PasteListener
    public void onKey(KeyPressEvent event) {
        System.out.println(event.key + " : "+ event.scancode);
    }
}
