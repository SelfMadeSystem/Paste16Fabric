package uwu.smsgamer.paste.paste16fabric.mixins.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste.paste16fabric.utils.Render2D;

@Mixin(Screen.class)
public class MixinScreen {
    @Shadow
    protected MinecraftClient client;

    @Inject(method = "sendMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    public void onMessage(String message, CallbackInfo ci) {
        if (message.startsWith(".")) {
            float f = Float.parseFloat(message.substring(1));
            Render2D.Z = f;
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("Set Z to: " + f), false);
            this.client.inGameHud.getChatHud().addToMessageHistory(message);
            ci.cancel();
        }
    }
}
