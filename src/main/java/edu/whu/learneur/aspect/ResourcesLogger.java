package edu.whu.learneur.aspect;

import edu.whu.learneur.constant.ResourcesType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourcesLogger {
    ResourcesType type();
}
