java-utils
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