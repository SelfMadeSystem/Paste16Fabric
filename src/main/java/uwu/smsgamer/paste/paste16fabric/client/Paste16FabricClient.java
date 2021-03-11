package uwu.smsgamer.paste.paste16fabric.client;

import net.fabricmc.api.*;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class Paste16FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("onInitializeClient");
//        MinecraftClient.getInstance().player.sendChatMessage();
    }
}
