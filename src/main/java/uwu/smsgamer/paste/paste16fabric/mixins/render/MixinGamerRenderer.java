package uwu.smsgamer.paste.paste16fabric.mixins.render;

import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.smsgamer.paste.paste16fabric.utils.Render2D;

import java.awt.*;

@Mixin(GameRenderer.class)
public class MixinGamerRenderer {
    float add = 0;
    @Inject(method = "renderWorld", at = @At("RETURN"))
    public void renderWorld(float tickDelta, long limitTime, MatrixStack matrix, CallbackInfo ci) {
        Matrix4f identity = Matrix4f.scale(1, 1, 1);
        add += tickDelta * 5;
        Render2D.drawHollowCircleSegm(identity, 0f, 0f, 1f, 0.2f,
          -80 + add, 80 + add, -88 + add, 88 + add,
          Color.WHITE);
        Render2D.drawHollowCircleSegm(identity, 0f, 0f, 1f, 0.2f,
          100 + add, 260 + add, 92 + add, 268 + add,
          Color.RED);
        Render2D.drawCircle(identity, 0, 0, 0.15f, Color.GRAY);
        Render2D.drawBorderedRect(identity, 0, 0, 0.5f, 0.5f, 0.05f, new Color(155, 155, 155, 50), new Color(255, 155, 155, 50));

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
