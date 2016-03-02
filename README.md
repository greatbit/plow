PLOW
==========
[![Jenkins Build](http://azee.people.yandex.net/jenkins/buildStatus/icon?job=plow)](http://azee.people.yandex.net/jenkins/job/plow)

About
==========
A simple tool to build scalable pluggable applications. 

Just add a dependency with a plugin and get its funtionality running.
 
Plugins can be accessed by types and names. Types and names could be retrieved - easy UI integration - no need to update a UI if a new pluggable functionality added. 
Just pass a type and name (lists could be retrieved as well) to make plugin do what you want.

Plugins are Spring singletons but there is no need for plugins to depend from Spring.



Why not simply ApplicationContext.getBeansOfType? 

It is structured. Access to bean takes O(1) instead of O(n). All discovery happens on app startup.

You can set custom names - useful for UI integrations.

May have beans of the same interfaces but not all of them have to be accessable as a plugin.

External plugin don't have to use Spring dependencies (e.g. for annotations) - no version collisions in runtime.
 
No need to update (add interfaces) in spring config lookup section.

Tutorial
==========
1. Create an interface (contract) for your plugin. I recommend to do it in a separate module (no need for plugin to depend on system internals).

2. Add a dependency to your contract:
```
<dependency>
    <groupId>ru.greatbit.plow</groupId>
    <artifactId>plow-contract</artifactId>
    <version>${latest.version}</version>
<dependency/>    
```

3. Create a plugin implementing your contract

4. Annotate your plugin class with ```@Plugin``` annotation
In annotation plugin name could be defined (will be a class name if missing).
Set your interface as a "contract" in annotation

5. Add you plugin to application classpath (e.g. - add maven dependency)

6. Add following dependencies to application:
```
<dependency>
    <groupId>ru.greatbit.plow</groupId>
    <artifactId>plow-contract</artifactId>
    <version>${latest.version}</version>
<dependency/>
<dependency>
    <groupId>ru.greatbit.plow</groupId>
    <artifactId>plow-core</artifactId>
    <version>${latest.version}</version>
<dependency/>
```

7. Add Plow to application Spring Context:
```
<bean class="ru.greatbit.plow.PluginsPostProcessor"/>
```

8. Add a component scan for your plugins namespace:
```
<context:component-scan base-package="ru.greatbit.plow">
    <context:include-filter type="annotation" expression="ru.greatbit.plow.contract.Plugin"/>
</context:component-scan>
```
(base-package="ru.greatbit.plow" - change it to your plugins packages)

9. @Autowire PluginsContainer whenever you need to use plugins. Get them using PluginsContainer methods by classes (types) and names. 
Get lists of types and names. 
Iterate through plugins. 