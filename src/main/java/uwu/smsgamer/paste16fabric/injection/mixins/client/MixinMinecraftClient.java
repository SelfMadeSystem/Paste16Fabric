package uwu.smsgamer.paste16fabric.injection.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste16fabric.Paste16Fabric;
import uwu.smsgamer.paste16fabric.injection.interfaces.client.IMinecraft;
import uwu.smsgamer.paste16fabric.injection.interfaces.render.ICamera;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient implements IMinecraft {

    @Shadow @Final public GameRenderer gameRenderer;

    @Inject(method = "stop", at = @At("HEAD"))
    public void stop(CallbackInfo ci) {
        Paste16Fabric.getInstance().onDisable();
    }

    @Inject(method = "run", at = @At("HEAD"))
    public void start(CallbackInfo ci) {
        Paste16Fabric.getInstance().onEnable();
    }

    @Override
    public ICamera getCamera() {
        return ((ICamera) gameRenderer.getCamera());
    }
}
