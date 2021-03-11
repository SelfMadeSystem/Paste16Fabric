package uwu.smsgamer.paste.paste16fabric.mixins.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class MixinScreen {
    @Inject(method = "sendMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    public void onMessage(String message, CallbackInfo ci) {
        if (message.startsWith(".")) {
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("Hi"), false);
            ci.cancel();
        }
    }
}
