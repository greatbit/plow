package ru.greatbit.plow;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.greatbit.plow.contract.Plugin;


import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by azee on 01.03.16.
 */
public class PluginsPostProcessor implements BeanPostProcessor {

    @Autowired
    private PluginsContainer pluginContainer;

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Plugin annotation = o.getClass().getAnnotation(Plugin.class);
        if (annotation != null){
            String name = isEmpty(annotation.name()) ? o.getClass().getSimpleName().toLowerCase() : annotation.name();

            Map<String, Map<String, Object>> plugins = pluginContainer.getPlugins();
            Map <String, Object> pluginsForClass = plugins.get(annotation.contract().getSimpleName());

            if (pluginsForClass == null){
                pluginsForClass = new HashMap<>();
            }
            pluginsForClass.put(name, o);
            pluginContainer.getPlugins().put(annotation.contract().getSimpleName(), pluginsForClass);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}
