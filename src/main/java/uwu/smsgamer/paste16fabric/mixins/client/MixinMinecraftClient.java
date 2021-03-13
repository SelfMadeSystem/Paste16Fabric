package uwu.smsgamer.paste16fabric.mixins.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste16fabric.Paste16Fabric;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "stop", at = @At("HEAD"))
    public void stop(CallbackInfo ci) {
        Paste16Fabric.getInstance().onDisable();
    }

    @Inject(method = "run", at = @At("HEAD"))
    public void start(CallbackInfo ci) {
        Paste16Fabric.getInstance().onEnable();
    }
}
