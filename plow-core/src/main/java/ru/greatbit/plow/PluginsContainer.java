package ru.greatbit.plow;

import org.springframework.stereotype.Component;
import ru.greatbit.plow.error.PluginNotFoundException;

import java.util.*;

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

    public List<String> getPluginsNamesList(Class clazz) {
        return getPluginsNamesList(clazz.getSimpleName());
    }

    public List<String> getPluginsNamesList(String clazz) {
        Map<String, Object> pluginsForClass = getPlugins().get(clazz);
        return pluginsForClass == null ? new ArrayList<String>() : new ArrayList<>(pluginsForClass.keySet());
    }

    public <T> Map<String, T> getPlugins(Class<T> clazz) {
        return getPlugins(clazz.getSimpleName());
    }

    public <T> Map<String, T> getPlugins(String clazz) {
        Map<String, T> pluginsForClass = (Map<String, T>) getPlugins().get(clazz);
        return pluginsForClass == null ? new HashMap<String, T>() : pluginsForClass;
    }

    public List<String> getPluginsTypes() {
        return new ArrayList<>(getPlugins().keySet());
    }

    public <T> T getPlugin(Class<T> clazz, String name){
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
