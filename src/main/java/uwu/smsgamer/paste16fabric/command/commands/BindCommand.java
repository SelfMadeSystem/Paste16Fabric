package uwu.smsgamer.paste16fabric.command.commands;

import com.mojang.brigadier.suggestion.Suggestions;
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
                PasteModule moduleByName = ModuleManager.getInstance().getModuleByNameIgnoreCase(args[0]);
                if (moduleByName != null) {
                    ChatUtils.sendMessage("Press a key...");
                    currentModule = moduleByName;
                } else {
                    ChatUtils.sendMessage("That module does not exist.");
                }
                break;
            case 2:
                moduleByName = ModuleManager.getInstance().getModuleByNameIgnoreCase(args[0]);
                if (moduleByName == null) {
                    ChatUtils.sendMessage("That module does not exist.");
                    return;
                }
                String arg1 = args[1].toLowerCase();
                switch (arg1) {
                    case "get":
                        ChatUtils.sendMessage(moduleByName.getName() + " is bound to: " + moduleByName.getKeyBind().getKey().getLocalizedText().asString());
                        break;
                    case "reset":
                    case "none":
                    case "null":
                        moduleByName.getKeyBind().setValue(-1);
                        ChatUtils.sendMessage(moduleByName.getName() + " bound to: " + moduleByName.getKeyBind().getKey().getLocalizedText().asString());
                }
        }
    }

    @Override
    public Suggestions tabComplete(String label, String[] args) {
        return null;
    }

    @PasteListener
    public void onKey(KeyPressEvent event) {
        if (currentModule != null && event.pressType == 1) {
            currentModule.getKeyBind().setValue(event.key);
            ChatUtils.sendMessage(String.format("%s bound to %s.", currentModule.getName(), currentModule.getKeyBind().getKey().getLocalizedText().asString()));
            currentModule = null;
        }
//        System.out.println(event.key + " : " + event.scancode);
    }
}
