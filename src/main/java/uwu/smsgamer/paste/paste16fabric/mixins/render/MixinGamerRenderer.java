package uwu.smsgamer.paste.paste16fabric.mixins.render;

import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste.paste16fabric.utils.Render2D;

import java.awt.*;

@Mixin(GameRenderer.class)
public class MixinGamerRenderer {
    @Inject(method = "renderHand", at = @At("RETURN"))
    public void renderHand(MatrixStack matrixStack, Camera camera, float tickDelta, CallbackInfo ci) {
//        float add = 0;
//        Render2D.drawHollowCircleSegm(matrixStack, 0f, 0f, 1f, 0.2f,
//          -80 + add, 80 + add, -88 + add, 88 + add,
//          Color.WHITE);
//        Render2D.drawHollowCircleSegm(matrixStack, 0f, 0f, 1f, 0.2f,
//          100 + add, 260 + add, 92 + add, 268 + add,
//          Color.RED);
//        Render2D.drawCircle(matrixStack, 0, 0, 0.15f, Color.GRAY);
        Render2D.drawRect(matrixStack, 0, 0, 0.5f, 0.5f, Color.GRAY);

        /*BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();

        matrixStack.push();
        Matrix4f matrix4f = matrixStack.peek().getModel();
        bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, -0.5F, -0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).next();
        bufferBuilder.vertex(matrix4f, 0.5F, -0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).next();
        bufferBuilder.vertex(matrix4f, 0.5F, 0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).next();
        bufferBuilder.vertex(matrix4f, -0.5F, 0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).next();
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        matrixStack.pop();

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(515);*/
    }
}
