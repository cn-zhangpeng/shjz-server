package com.shjz.zp95sky.shjz.server.annotation;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.lang.annotation.*;

/**
 * Mock 测试注解
 * @author 山海紫穹
 * @date 2021年06月22日 14:10
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@BaseTestAnnotation
@AutoConfigureMockMvc
public @interface MockTestAnnotation {
}
