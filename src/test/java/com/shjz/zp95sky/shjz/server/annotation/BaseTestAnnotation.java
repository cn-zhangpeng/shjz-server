package com.shjz.zp95sky.shjz.server.annotation;

import com.shjz.zp95sky.shjz.server.MySpaceServerApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootTest(classes = {MySpaceServerApplication.class})
@ActiveProfiles({"test"})
@interface BaseTestAnnotation {
}
