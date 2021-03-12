package uwu.smsgamer.paste16fabric.config;

import org.yaml.snakeyaml.*;
import uwu.smsgamer.paste16fabric.Paste16Fabric;

import java.io.*;
import java.util.*;

public class ConfigManager {
    private static ConfigManager instance;

    public static ConfigManager getInstance() {
        if (instance == null) instance = new ConfigManager();
        return instance;
    }

    private final Map<String, Object> objectMap = new LinkedHashMap<>();

    public void loadConfig(String name) {
        loadConfig(new File(Paste16Fabric.getModDirectory(), name));
    }

    public void loadConfig(File file) {
        if (!file.exists()) return;
        try (Reader reader = new FileReader(file)) {
            loadConfig(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadConfig(Reader reader) {
        Yaml yaml = new Yaml();
        Map<String, Object> load = yaml.load(reader);
        if (load == null) return;
        for (ConfigValue<?> value : values) loadAndSet(value, load);
    }

    private void loadAndSet(ConfigValue<?> v, Map<String, Object> map) {
        String[] split = v.getPath().split("\\.");
        for (int i = 0; i < split.length - 1; i++) {
            String s = split[i];
            Object o = map.get(s);
            if (o instanceof Map) map = (Map<String, Object>) o;
            else {
                System.err.println(v.getPath() + " couldn't be set. "
                  + (o == null ? "Object not there." : "Actual type: " + o.getClass().getName()));
                return;
            }
        }
        Object o = map.get(split[split.length - 1]);
        if (v.defaultValue.getClass().isInstance(o)) v.setValue(o);
        else System.err.println(v.getPath() + " couldn't be set. "
          + (o == null ? "Object not there." : "Actual type: " + o.getClass().getName()));
    }

    private Set<ConfigValue<?>> values = new HashSet<>();

    void addConfigValue(ConfigValue<?> v) {
        setValue(v, false);
        values.add(v);
    }

    public <T> void setValue(ConfigValue<T> v, boolean set) {
        Map<String, Object> map = objectMap;
        StringBuilder sb = new StringBuilder();
        String[] split = v.getPath().split("\\.");
        for (int i = 0; i < split.length - 1; i++) {
            String s = split[i];
            sb.append(s);
            Object o = map.get(s);
            if (o == null) map.put(s, map = new LinkedHashMap<>());
            else {
                if (o instanceof Map) map = (Map<String, Object>) o;
                else
                    throw new IllegalStateException(sb + " is not of type Map! Actual type: " + o.getClass().getName());
            }
            sb.append(".");
        }
        if (set) map.put(split[split.length - 1], v.value);
        else map.putIfAbsent(split[split.length - 1], v.defaultValue);
    }

    public void saveConfig(String name) {
        saveConfig(new File(Paste16Fabric.getModDirectory(), name));
    }

    public void saveConfig(File file) {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        saveConfig(file, options);
    }

    public void saveConfig(File file, DumperOptions options) {
        Yaml yaml = new Yaml(options);

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();

            yaml.dump(objectMap, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
