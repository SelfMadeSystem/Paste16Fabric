package uwu.smsgamer.paste.paste16fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import uwu.smsgamer.paste.paste16fabric.utils.Render2D;

import java.awt.*;
import java.io.File;

public class Paste16Fabric implements ModInitializer {
    public static File getModDirectory() {
        return MinecraftClient.getInstance().runDirectory;
    }

    @Override
    public void onInitialize() {
        System.out.println("onInitialize");

//        HudRenderCallback.EVENT.register((matrixStack, b) -> {

//        });
    }
}
