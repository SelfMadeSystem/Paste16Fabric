package uwu.smsgamer.paste16fabric.injection.mixins.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste16fabric.events.EventManager;
import uwu.smsgamer.paste16fabric.events.events.RenderEvent;
import uwu.smsgamer.paste16fabric.injection.interfaces.render.ICamera;
import uwu.smsgamer.paste16fabric.module.defaultModules.player.BetterRotation;

@SuppressWarnings("FieldMayBeFinal")
@Mixin(GameRenderer.class)
public class MixinGamerRenderer {
    @Final
    @Shadow
    private MinecraftClient client;
    @Shadow
    private float viewDistance;
    @Shadow
    private int ticks;
    @Shadow
    private boolean renderHand = true;
    @Final
    @Shadow
    private LightmapTextureManager lightmapTextureManager;
    @Final
    @Shadow
    private Camera camera;

    @Inject(method = "render", at = @At("RETURN"))
//value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;render(Lnet/minecraft/client/util/math/MatrixStack;F)V"))
    public void render(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        EventManager.call(new RenderEvent(tickDelta, new MatrixStack(), RenderEvent.Place.POST));
    }

    /**
     * @author Sms_Gamer_3808
     */
    @Overwrite
    public void renderWorld(float tickDelta, long limitTime, MatrixStack matrix) {
        this.lightmapTextureManager.update(tickDelta);
        if (this.client.getCameraEntity() == null) {
            this.client.setCameraEntity(this.client.player);
        }

        this.updateTargetedEntity(tickDelta);
        this.client.getProfiler().push("center");
        boolean bl = this.shouldRenderBlockOutline();
        this.client.getProfiler().swap("camera");
        this.viewDistance = (float) (this.client.options.viewDistance * 16);
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.peek().getModel().multiply(this.getBasicProjectionMatrix(this.camera, tickDelta, true));
        this.bobViewWhenHurt(matrixStack, tickDelta);
        if (this.client.options.bobView) {
            this.bobView(matrixStack, tickDelta);
        }

        float f = MathHelper.lerp(tickDelta, this.client.player.lastNauseaStrength, this.client.player.nextNauseaStrength) * this.client.options.distortionEffectScale * this.client.options.distortionEffectScale;
        if (f > 0.0F) {
            int i = this.client.player.hasStatusEffect(StatusEffects.NAUSEA) ? 7 : 20;
            float g = 5.0F / (f * f + 5.0F) - f * 0.04F;
            g *= g;
            Vector3f vector3f = new Vector3f(0.0F, MathHelper.SQUARE_ROOT_OF_TWO / 2.0F, MathHelper.SQUARE_ROOT_OF_TWO / 2.0F);
            matrixStack.multiply(vector3f.getDegreesQuaternion(((float) this.ticks + tickDelta) * (float) i));
            matrixStack.scale(1.0F / g, 1.0F, 1.0F);
            float h = -((float) this.ticks + tickDelta) * (float) i;
            matrixStack.multiply(vector3f.getDegreesQuaternion(h));
        }

        Matrix4f matrix4f = matrixStack.peek().getModel();
        this.loadProjectionMatrix(matrix4f);
        this.camera.update(this.client.world, this.client.getCameraEntity() == null ? this.client.player : this.client.getCameraEntity(), !this.client.options.getPerspective().isFirstPerson(), this.client.options.getPerspective().isFrontView(), tickDelta);
//        matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(this.camera.getPitch()));
//        matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(this.camera.getYaw() + 180.0F));
//        matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(((ICamera) this.camera).getRoll()));
        // 123 132 213 231 312 321
        // xyz xzy yxz yzx zxy zyx

        if (BetterRotation.getInstance().implementRoll.getValue()) {
            ICamera camera = ((ICamera) this.camera);
            Vec3d euler = camera.getEuler();
            switch (BetterRotation.getInstance().test1.getInt()) {
                case 0: {
                    matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((float) euler.x));
                    matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) euler.y));
                    matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float) euler.z));
                    break;
                }
                case 1: {
                    matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((float) euler.x));
                    matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float) euler.z));
                    matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) euler.y));
                    break;
                }
                case 2: {
                    matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) euler.y));
                    matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((float) euler.x));
                    matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float) euler.z));
                    break;
                }
                case 3: {
                    matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) euler.y));
                    matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float) euler.z));
                    matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((float) euler.x));
                    break;
                }
                case 4: {
                    matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float) euler.z));
                    matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((float) euler.x));
                    matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) euler.y));
                    break;
                }
                case 5: {
                    matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float) euler.z));
                    matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) euler.y));
                    matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((float) euler.x));
                    break;
                }
            }
        } else {
            matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(((ICamera) this.camera).getRoll()));
            matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(this.camera.getPitch()));
            matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(this.camera.getYaw() + 180.0F));
        }
//        matrix.multiply(this.camera.getRotation());
        this.client.worldRenderer.render(matrix, tickDelta, limitTime, bl, this.camera, this.client.gameRenderer, this.lightmapTextureManager, matrix4f);
        this.client.getProfiler().swap("hand");
        if (this.renderHand) {
            RenderSystem.clear(256, MinecraftClient.IS_SYSTEM_MAC);
            this.renderHand(matrix, camera, tickDelta);
        }

        this.client.getProfiler().pop();
    }

    @Shadow
    private void bobViewWhenHurt(MatrixStack matrixStack, float tickDelta) {
    }

    @Shadow
    public Matrix4f getBasicProjectionMatrix(Camera camera, float tickDelta, boolean b) {
        return null;
    }

    @Shadow
    private void bobView(MatrixStack matrixStack, float tickDelta) {
    }

    @Shadow
    public void loadProjectionMatrix(Matrix4f matrix4f) {
    }

    @Shadow
    private void renderHand(MatrixStack matrix, Camera camera, float tickDelta) {
    }

    @Shadow
    private boolean shouldRenderBlockOutline() {
        return false;
    }

    @Shadow
    public void updateTargetedEntity(float tickDelta) {
    }
}
