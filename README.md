Why not simply ApplicationContext.getBeansOfType? 
It is structured. Access to bean takes O(1) instead of O(n). All discovery happens on app startup.
You can set custom names - useful for UI integrations.
May have beans of the same interfaces but not all of them have to be accessable as a plugin.
External plugin don't have to use Spring dependencies (e.g. for annotations) - no version collisions in runtime. 
No need to update (add interfaces) in spring config lookup section.