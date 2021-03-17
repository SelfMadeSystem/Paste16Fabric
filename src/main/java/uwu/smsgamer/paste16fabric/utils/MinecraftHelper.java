package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.client.MinecraftClient;
import uwu.smsgamer.paste16fabric.injection.interfaces.IMinecraft;

public interface MinecraftHelper {
    MinecraftClient mc = MinecraftClient.getInstance();
    IMinecraft imc = (IMinecraft) mc;
}
