package com.shjz.zp95sky.shjz.server.annotation;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.lang.annotation.*;

/**
 * Mock 测试注解
 * @author 华夏紫穹
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@BaseTestAnnotation
@AutoConfigureMockMvc
public @interface MockTestAnnotation {
}
