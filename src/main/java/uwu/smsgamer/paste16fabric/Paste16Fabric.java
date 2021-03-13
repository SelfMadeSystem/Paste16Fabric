package uwu.smsgamer.paste16fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import uwu.smsgamer.paste16fabric.command.CommandManager;
import uwu.smsgamer.paste16fabric.config.ConfigManager;
import uwu.smsgamer.paste16fabric.module.ModuleManager;

import java.io.File;

public class Paste16Fabric implements ModInitializer {
    private static Paste16Fabric instance;
    public static boolean enabled = false;

    public static Paste16Fabric getInstance() {
        if (instance == null) instance = new Paste16Fabric();
        return instance;
    }

    public static File getModDirectory() {
        return new File(MinecraftClient.getInstance().runDirectory, "PasteClient");
    }

    @Override
    public void onInitialize() {
    }

    public void onEnable() {
        System.out.println("Starting Paste16Fabric...");

        ModuleManager.getInstance();
        CommandManager.getInstance();

        ConfigManager.getInstance().loadConfig("config.yml");

        ModuleManager.getInstance().init();
        CommandManager.getInstance().init();
    }

    public void onDisable() {
        ConfigManager.getInstance().saveConfig("config.yml");
    }
}
