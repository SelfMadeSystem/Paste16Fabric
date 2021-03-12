package uwu.smsgamer.paste16fabric.mixins.client;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste16fabric.events.EventManager;
import uwu.smsgamer.paste16fabric.events.events.*;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        EventManager.call(new KeyPressEvent(window, key, scancode, i, j));
    }

    @Inject(method = "onChar", at = @At("HEAD"))
    public void onChar(long window, int chr, int modifiers, CallbackInfo ci) {
        EventManager.call(new CharEvent(window, (char) chr, modifiers));
    }
}
