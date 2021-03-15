package uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors;

import net.minecraft.client.gui.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.*;
import uwu.smsgamer.paste16fabric.gui.clickgui.AbstractClickGui;
import uwu.smsgamer.paste16fabric.utils.MinecraftHelper;
import uwu.smsgamer.paste16fabric.values.Val;

import java.util.*;

public abstract class AbstractValueEditor<T> extends AbstractParentElement implements MinecraftHelper {
    protected final Val<T> val;
    protected final List<Element> children = new LinkedList<>();

    protected AbstractValueEditor(Val<T> thisVal) {
        this.val = thisVal;
        for (Val<?> val : this.val.getVals()) {
            children.add(val.getValueEditor());
        }
    }

    @Override
    public List<? extends Element> children() {
        return children;
    }

    public abstract Vec2f render(MatrixStack stack, AbstractClickGui gui, float x, float y, float w, float h);
}
