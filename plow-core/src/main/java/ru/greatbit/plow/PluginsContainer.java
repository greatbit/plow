package ru.greatbit.plow;

import org.springframework.stereotype.Component;
import ru.greatbit.plow.error.PluginNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by azee on 01.03.16.
 */
@Component
public class PluginsContainer {
    private Map<String, Map<String, Object>> plugins;

    public Map<String, Map<String, Object>> getPlugins() {
        if (plugins == null){
            plugins = new HashMap<>();
        }
        return plugins;
    }

    public List<String> getPluginsList(String clazz) {
        Map<String, Object> pluginsForClass = getPlugins().get(clazz);
        return pluginsForClass == null ? new ArrayList<>() : new ArrayList<>(pluginsForClass.keySet());
    }

    public List<String> getPluginsTypes() {
        return new ArrayList<>(getPlugins().keySet());
    }

    public <T>T getPlugin(Class<T> clazz, String name){
        return clazz.cast(getPlugin(clazz.getSimpleName(), name));
    }

    public <T> T getPlugin(String clazz, String name){
        Map<String, Object> pluginsForClass = getPlugins().get(clazz);
        if (pluginsForClass == null){
            throw new PluginNotFoundException(String.format("Couldn't find a plugin with class [%s]", clazz));
        }
        T plugin = (T)pluginsForClass.get(name);

        if (plugin == null) {
            throw new PluginNotFoundException(String.format("Couldn't find a plugin with class [%s] and name [%s]", clazz, name));
        }
        return plugin;
    }
}
