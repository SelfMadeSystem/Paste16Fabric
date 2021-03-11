package uwu.smsgamer.paste.core.config;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ConfigValue<T> {
    @Getter
    @NotNull
    private final String path;
    @NotNull
    protected final T defaultValue;
    protected T value;

    public ConfigValue(@NotNull String path, @NotNull T defaultValue) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(defaultValue);
        this.path = path;
        this.defaultValue = defaultValue;
        ConfigManager.getInstance().addConfigValue(this);
    }

    public T getValue() {
        return value == null ? defaultValue : value;
    }

    void setValueWOConfig(Object o) {
        value = (T) o;
    }

    public void setValue(Object o) {
        if (defaultValue.getClass().isInstance(o)) setValueWOConfig(o);
        else
            throw new IllegalArgumentException("o is not of type " + defaultValue.getClass().getName() +
              ". Actual type: " + o.getClass().getName());
        ConfigManager.getInstance().setValue(this, true);
    }
}
