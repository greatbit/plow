package ru.greatbit.plow.contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by azee on 02.03.16.
 * An annotation used to detect plugins
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Plugin {
    String name() default "";
    Class contract();
}
