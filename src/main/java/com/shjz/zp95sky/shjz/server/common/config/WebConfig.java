package com.shjz.zp95sky.shjz.server.common.config;

import com.shjz.zp95sky.shjz.server.common.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author 华夏紫穹
 * @date 2021年06月08日 14:08
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Resource
    private LogInterceptor logInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }

}
