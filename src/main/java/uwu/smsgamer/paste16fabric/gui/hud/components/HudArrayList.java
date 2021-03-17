package uwu.smsgamer.paste16fabric.gui.hud.components;

import lombok.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Matrix4f;
import uwu.smsgamer.paste16fabric.gui.hud.HudComponent;
import uwu.smsgamer.paste16fabric.module.*;
import uwu.smsgamer.paste16fabric.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class HudArrayList extends HudComponent {
    @Getter
    @Setter
    protected Colour boxColour = new RGB(0, 0, 0, 0.3);
    protected Colour textColour = new RGB(1, 1, 1, 1);
    protected float height;
    protected float width;

    @Override
    public void onRender(MatrixStack matrices, float partialTicks) {
        List<PasteModule> modules = ModuleManager.getInstance().getEnabledModules();
        List<Text> moduleNames = modules.stream().map(PasteModule::getVisibleName)
          .sorted(Comparator.comparingDouble((Text s) -> {
              float w = getStrWidth(s);
              width = Math.max(w, width);
              return w;
          }).reversed()).collect(Collectors.toList());

        height = getStrHeight() * moduleNames.size();

        float x = getX();
        float y = getY();

        for (int i = 0; i < moduleNames.size(); i++) {
            Text text = moduleNames.get(i);

            drawText(matrices, text, i, x, y);
        }
    }

    private float getStrWidth(Text s) {
        return mc.textRenderer.getWidth(s);
    }

    private float getStrHeight() {
        return mc.textRenderer.fontHeight + 2;
    }

    private void drawText(MatrixStack matrices, Text text, int i, float x, float y) {
        Matrix4f matrix = matrices.peek().getModel();
        float strW = getStrWidth(text);
        switch (horizontalAlignment) {
            case -1: {
                Render2D.drawRect(matrix, x, y + getStrHeight() * i, x + strW + 2, y + getStrHeight() * (i + 1), boxColour);
                Render2D.drawString(matrix, text, x + 1, y + getStrHeight() * i + 1, -1, -1, false, textColour);
                break;
            }
            case 0: {
                float f = (width - strW) / 2;

                Render2D.drawRect(matrix, x + f - width / 2 - 1, y + getStrHeight() * i, x - f + width / 2, y + getStrHeight() * (i + 1), boxColour);
                Render2D.drawString(matrix, text, x, y + getStrHeight() * i + 1, 0, -1, false, textColour);
                break;
            }
            case 1: {
                Render2D.drawRect(matrix, x - strW - 1, y + getStrHeight() * i, x, y + getStrHeight() * (i + 1), boxColour);
                Render2D.drawString(matrix, text, x, y + getStrHeight() * i + 1, 1, -1, false, textColour);
                break;
            }
        }
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
