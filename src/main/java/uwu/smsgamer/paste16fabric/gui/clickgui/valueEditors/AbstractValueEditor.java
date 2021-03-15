package uwu.smsgamer.paste16fabric.gui.clickgui.valueEditors;

import net.minecraft.client.gui.Element;
import uwu.smsgamer.paste16fabric.gui.clickgui.*;
import uwu.smsgamer.paste16fabric.values.Val;

import java.util.*;

public abstract class AbstractValueEditor<T> extends AbstractClickComponent {
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
}
