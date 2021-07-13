package com.shjz.zp95sky.shjz.server.annotation;

import java.lang.annotation.*;

/**
 * 通用测试注解
 * @author 华夏紫穹
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@BaseTestAnnotation
public @interface CommonTestAnnotation {
}
