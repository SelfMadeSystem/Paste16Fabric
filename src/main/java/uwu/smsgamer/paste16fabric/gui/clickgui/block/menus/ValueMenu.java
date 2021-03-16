package uwu.smsgamer.paste16fabric.gui.clickgui.block.menus;

import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import uwu.smsgamer.paste16fabric.gui.clickgui.*;
import uwu.smsgamer.paste16fabric.gui.clickgui.block.*;
import uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors.AbstractValueEditor;
import uwu.smsgamer.paste16fabric.module.PasteModule;
import uwu.smsgamer.paste16fabric.values.Val;

import java.util.*;

// Todo: Clicking and other input stuff.
public class ValueMenu extends AbstractBlockMenu {
    public String name;
    public List<ValueBlock> children = new LinkedList<>();

    public ValueMenu(BlockClickGui gui, String name, List<Val<?>> vals) {
        super(gui, 200, 60);
        for (Val<?> val : vals) {
            children.add(new ValueBlock(val.getValueEditor(), this));
        }
        this.name = name;
    }

    public ValueMenu(BlockClickGui gui, List<AbstractValueEditor<?, ?>> editors, String name) {
        super(gui, 200, 60);
        for (AbstractValueEditor<?, ?> editor : editors) {
            children.add(new ValueBlock(editor, this));
        }
        this.name = name;
    }

    public ValueMenu(BlockClickGui gui, PasteModule module) {
        this(gui, module.getName(), module.getValues());
    }

    @Override
    public List<? extends AbstractClickComponent> children() {
        return children;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        childDrag = false;
        if (super.mouseClicked(mouseX, mouseY, button)) return true;
        if (button <= 1 && isMouseOver(mouseX, mouseY)) {
            for (ValueBlock child : children) {
                if (child.mouseClicked(mouseX, mouseY, button)) {
                    childDrag = true;
                    setDragging(true);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (super.mouseReleased(mouseX, mouseY, button)) return true;
        if (isDragging()) {
            setDragging(false);
            for (ValueBlock child : children) {
                if (child.mouseReleased(mouseX, mouseY, button)) return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) return true;
        if (isDragging()) {
            for (ValueBlock child : children) {
                if (child.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) return true;
            }
        }
        return false;
    }
}

class ValueBlock extends AbstractClickComponent {
    public final String name;
    public final AbstractValueEditor<?, ?> editor;
    public final ValueMenu menu;
    public final int textWidth;

    ValueBlock(AbstractValueEditor<?, ?> editor, ValueMenu menu) {
        this.editor = editor;
        this.menu = menu;
        this.name = editor.val.getName();
        this.textWidth = mc.textRenderer.getWidth(name);
    }

    @Override
    protected Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, double mouseX, double mouseY) {
        drawStringWithShadow(stack, mc.textRenderer, name, (int) (x + 2), (int) (y + 2), 0xFF404040);
        if (next) {
            editor.render(stack, gui, x + textWidth + 4, y + 1, mouseX, mouseY, width - 4 - textWidth, height - 2);
        } else {
            editor.render(stack, gui, x, y + mc.textRenderer.fontHeight + 2, mouseX, mouseY, width, height - mc.textRenderer.fontHeight - 2);
        }
        return new Vec2f(width, height);
    }

    private boolean next;

    @Override
    public Vec2f getSize(AbstractClickGui gui, float w, float h) {
        Vec2f editorSize = editor.getSize(gui, w, h);
        if (w - editorSize.x - textWidth - 4 >= 2) {
            next = true;
            return new Vec2f(textWidth + 4 + editorSize.x, Math.max(mc.textRenderer.fontHeight, editorSize.y) + 2);
        }
        next = false;
        return new Vec2f(Math.max(textWidth, editorSize.x), mc.textRenderer.fontHeight + 2 + editorSize.y);
    }

    @Override
    public List<? extends Element> children() {
        return Collections.singletonList(editor);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            if (button == 0) {
                editor.mouseClicked(mouseX, mouseY, button);
                setDragging(true);
            } else if (button == 1) {
                // TODO: 2021-03-15 Open val's children.
            }
            return button <= 1;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (isDragging()) {
            setDragging(false);
            editor.mouseReleased(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isDragging()) editor.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        return false;
    }
}