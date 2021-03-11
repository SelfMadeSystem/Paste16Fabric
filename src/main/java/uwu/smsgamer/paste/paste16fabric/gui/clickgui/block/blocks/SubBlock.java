/*----------------------------------------------------*\
|                                                      |
|    ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\    |
|   //      Copyright (c) 2020 Shoghi Simon       \\   |
|   \\   License: GNU GENERAL PUBLIC LICENSE V3   //   |
|    \\\\\\\\\\\\\\\\\\\\\\\///////////////////////    |
|                                                      |
\*----------------------------------------------------*/
package uwu.smsgamer.paste.paste16fabric.gui.clickgui.block.blocks;

import org.lwjgl.opengl.GL11;
import uwu.smsgamer.paste.paste16fabric.utils.Render2D;

import java.awt.*;

public abstract class SubBlock extends Block {
    public String name;

    public SubBlock(Block parent, float width, float height, float x, float y, String name) {
        super(parent, width, height, x, y);
        this.name = name;
        this.y = -topHeight/2f;
    }

    protected boolean overClose = false;

    protected void drawTop(float height) {
        double mouseX = mc.mouse.getX() - 250;
        double mouseY = -mc.mouse.getY() + 250;
        float minX = x - width / 2;
        float maxX = x + width / 2;
        float minY = y + height;
        float maxY = y + height + topHeight;
        Render2D.drawRect(Render2D.identity, minX, minY, maxX, maxY, TOP_COLOR);

        Render2D.drawString(name, minX + (maxX - minX) / 2, minY + (maxY - minY) / 2, 10, 0, Color.BLACK);
        float w = topHeight - 4;
        minX = maxX - w;
        maxX = maxX - 2;
        minY = maxY - w;
        maxY = maxY - 2;
        overClose = (mouseX > minX - 2 && mouseX < maxX - 2 && mouseY > minY - 2 && mouseY < maxY - 2);
        Color c = overClose && this.child == null ? new Color(255, 100, 0) : Color.RED;
        Render2D.drawRect(Render2D.identity, minX, minY, maxX, maxY, c);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glColor4f(0, 0, 0, 0);
        GL11.glVertex2d((maxX-1)/250, (maxY-1)/250);
        GL11.glVertex2d((minX-1)/250, (minY-1)/250);
        GL11.glVertex2d((maxX-1)/250, (minY-1)/250);
        GL11.glVertex2d((minX-1)/250, (maxY-1)/250);
        GL11.glEnd();
    }

    @Override
    public void click() {
        if (overClose && this.child == null) parent.close();
    }
}
