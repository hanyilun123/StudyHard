package com.study.hard.config.DoubleDataSourceConfig;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value() default "primary"; //该值即key值，默认使用默认数据库
}
