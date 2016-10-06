package com.srtianxia;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by srtianxia on 2016/9/29.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface GET {
    String value() default "";
}
