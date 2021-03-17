package uwu.smsgamer.paste16fabric.injection.mixins.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste16fabric.command.CommandManager;

@Mixin(Screen.class)
public class MixinScreen {
    @Shadow
    protected MinecraftClient client;

    @Inject(method = "sendMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    public void onMessage(String message, CallbackInfo ci) {
        if (CommandManager.getInstance().handleCommand(message)) {
            ci.cancel();
            this.client.inGameHud.getChatHud().addToMessageHistory(message);
        }
    }
}
