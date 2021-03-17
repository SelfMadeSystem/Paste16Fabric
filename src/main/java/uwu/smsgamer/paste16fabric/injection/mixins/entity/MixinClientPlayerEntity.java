package uwu.smsgamer.paste16fabric.injection.mixins.entity;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste16fabric.events.EventManager;
import uwu.smsgamer.paste16fabric.events.events.UpdateEvent;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    @Inject(method = "tickMovement", at = @At("RETURN"))
    public void onUpdate(CallbackInfo ci) {
        EventManager.call(new UpdateEvent());
    }
}
