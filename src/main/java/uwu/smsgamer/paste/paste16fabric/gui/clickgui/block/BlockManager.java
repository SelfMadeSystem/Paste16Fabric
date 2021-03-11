/*----------------------------------------------------*\
|                                                      |
|    ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\    |
|   //      Copyright (c) 2020 Shoghi Simon       \\   |
|   \\   License: GNU GENERAL PUBLIC LICENSE V3   //   |
|    \\\\\\\\\\\\\\\\\\\\\\\///////////////////////    |
|                                                      |
\*----------------------------------------------------*/
package uwu.smsgamer.paste.paste16fabric.gui.clickgui.block;

import net.minecraft.client.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import uwu.smsgamer.paste.paste16fabric.gui.clickgui.block.blocks.*;
import uwu.smsgamer.paste.paste16fabric.utils.MinecraftHelper;

public class BlockManager extends Screen implements MinecraftHelper {
    private static BlockManager instance;

    protected BlockManager(Text title) {
        super(title);
    }

    public static BlockManager getInstance() {
        if (instance == null) instance = new BlockManager(null);
        return instance;
    }

    private final Block mainBlock = new MainBlock();

    public final void render() {

        render(mainBlock);
        click = mc.mouse.wasLeftButtonClicked();
    }

    private boolean click;

    private void render(Block block) {
        block.render();
        if (block.child != null) render(block.child);
        if (click && !mc.mouse.wasLeftButtonClicked()) block.unclick();
        else if (!click && mc.mouse.wasLeftButtonClicked()) block.click();
    }

    public void charKey(char c) {
        charKey(mainBlock, c);
    }

    private void charKey(Block b, char c) {
        b.charKey(c);
        if (b.child != null) charKey(b.child, c);
    }

    public void key(int key) {
        key(mainBlock, key);
    }

    private void key(Block b, int key) {
        b.key(key);
        if (b.child != null) key(b.child, key);
    }
}
