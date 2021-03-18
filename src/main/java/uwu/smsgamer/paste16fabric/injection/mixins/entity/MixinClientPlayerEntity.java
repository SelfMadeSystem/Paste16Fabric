package uwu.smsgamer.paste16fabric.injection.mixins.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.c2s.play.*;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste16fabric.events.EventManager;
import uwu.smsgamer.paste16fabric.events.events.*;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity extends AbstractClientPlayerEntity {

    @Shadow private boolean lastSprinting;

    @Shadow @Final public ClientPlayNetworkHandler networkHandler;

    @Shadow private boolean lastSneaking;

    @Shadow protected abstract boolean isCamera();

    @Shadow private double lastX;

    @Shadow private double lastBaseY;

    @Shadow private double lastZ;

    @Shadow private float lastYaw;

    @Shadow private float lastPitch;

    @Shadow private int ticksSinceLastPositionPacketSent;

    @Shadow private boolean lastOnGround;

    @Shadow private boolean autoJumpEnabled;

    @Shadow @Final protected MinecraftClient client;

    public MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "tickMovement", at = @At("RETURN"))
    public void onUpdate(CallbackInfo ci) {
        EventManager.call(new UpdateEvent());
    }

    /**
     * @author Sms_Gamer_3808
     */
    @Overwrite
    private void sendMovementPackets() {
        SendMovementPacketsEvent event = new SendMovementPacketsEvent(this.onGround);

        boolean bl = this.isSprinting();
        if (bl != this.lastSprinting) {
            ClientCommandC2SPacket.Mode mode = bl ? ClientCommandC2SPacket.Mode.START_SPRINTING : ClientCommandC2SPacket.Mode.STOP_SPRINTING;
            event.sprintPacket = new ClientCommandC2SPacket(this, mode);
            this.lastSprinting = bl;
        }

        boolean bl2 = this.isSneaking();
        if (bl2 != this.lastSneaking) {
            ClientCommandC2SPacket.Mode mode2 = bl2 ? ClientCommandC2SPacket.Mode.PRESS_SHIFT_KEY : ClientCommandC2SPacket.Mode.RELEASE_SHIFT_KEY;
            event.sneakPacket = new ClientCommandC2SPacket(this, mode2);
            this.lastSneaking = bl2;
        }

        if (this.isCamera()) {
            double d = this.getX() - this.lastX;
            double e = this.getY() - this.lastBaseY;
            double f = this.getZ() - this.lastZ;
            double g = this.yaw - this.lastYaw;
            double h = this.pitch - this.lastPitch;
            ++this.ticksSinceLastPositionPacketSent;
            boolean bl3 = d * d + e * e + f * f > 9.0E-4D || this.ticksSinceLastPositionPacketSent >= 20;
            boolean bl4 = g != 0.0D || h != 0.0D;
            if (this.hasVehicle()) {
                Vec3d vec3d = this.getVelocity();
                event.movePacket = new PlayerMoveC2SPacket.Both(vec3d.x, -999.0D, vec3d.z, this.yaw, this.pitch, this.onGround);
                bl3 = false;
            } else if (bl3 && bl4) {
                event.movePacket = new PlayerMoveC2SPacket.Both(this.getX(), this.getY(), this.getZ(), this.yaw, this.pitch, this.onGround);
            } else if (bl3) {
                event.movePacket = new PlayerMoveC2SPacket.PositionOnly(this.getX(), this.getY(), this.getZ(), this.onGround);
            } else if (bl4) {
                event.movePacket = new PlayerMoveC2SPacket.LookOnly(this.yaw, this.pitch, this.onGround);
            } else if (this.lastOnGround != this.onGround) {
                event.movePacket = new PlayerMoveC2SPacket(this.onGround);
            }

            if (bl3) {
                this.lastX = this.getX();
                this.lastBaseY = this.getY();
                this.lastZ = this.getZ();
                this.ticksSinceLastPositionPacketSent = 0;
            }

            if (bl4) {
                this.lastYaw = this.yaw;
                this.lastPitch = this.pitch;
            }

            this.lastOnGround = this.onGround;
            this.autoJumpEnabled = this.client.options.autoJump;
        }

        EventManager.call(event);
        event.sendAllPackets();
    }
}
