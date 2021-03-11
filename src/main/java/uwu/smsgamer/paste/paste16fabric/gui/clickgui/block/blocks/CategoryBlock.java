/*----------------------------------------------------*\
|                                                      |
|    ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\    |
|   //      Copyright (c) 2020 Shoghi Simon       \\   |
|   \\   License: GNU GENERAL PUBLIC LICENSE V3   //   |
|    \\\\\\\\\\\\\\\\\\\\\\\///////////////////////    |
|                                                      |
\*----------------------------------------------------*/
package uwu.smsgamer.paste.paste16fabric.gui.clickgui.block.blocks;

import uwu.smsgamer.paste.paste16fabric.utils.Render2D;

import java.awt.*;

public class CategoryBlock extends SubBlock {
    int amount;
    String[] categories;

    public CategoryBlock(Block parent, String category) {
        super(parent, blockWidth, blockHeight, 0, 0, category);
        categories = Stuff.values.get(category).keySet().toArray(new String[0]);
        amount = categories.length;
    }

    int selected = -1;
    int hover = -1;

    @Override
    public void render() {
        Render2D.drawRect(Render2D.identity, -1, -10, 20, 1, new Color(0, 0, 0, 150));
        x = -(children() - 1) * 50;
        hover = -1;
        float minX = (x - (width / 2));
        float maxX = (x + (width / 2));
        Render2D.drawRect(Render2D.identity, minX, (y - (height * (amount / 2f)) / 2) + edgeRound, maxX,
          (y + (height * (amount / 2f)) / 2), BORDERED_COLOR);
        drawTop(height * (amount / 2f) / 2);
        for (int i = 0; i < amount; i++) {
            float minY = ((y + (height / 2 * (i - amount / 2f))));
            float maxY = (y + (height / 2 * (i - amount / 2f)) + (height / 2));
            if (isHoveringRaw(minX, maxX, minY+edgeRadius/3, maxY-edgeRadius/3)) hover = i;
            Color c = selected == i ? SELECTED_COLOR : hover == i && child == null ? HOVER_COLOR : MAIN_COLOR;
            Render2D.drawRoundBorderedRect(minX, minY, maxX, maxY, edgeRound, edgeRadius, c, BORDERED_COLOR);
            Render2D.drawString(categories[i], minX + edgeRadius + 2, minY + (maxY - minY) / 2, 20, -1, Color.BLACK);
        }
    }

    @Override
    public void click() {
        super.click();
        if (this.parent.child == this && this.child == null && this.hover != -1) {
            this.child = new ModuleBlock(this, name, categories[this.hover]);
            this.selected = hover;
        }
    }

    @Override
    public void close() {
        this.child = null;
        this.selected = -1;
    }
}
