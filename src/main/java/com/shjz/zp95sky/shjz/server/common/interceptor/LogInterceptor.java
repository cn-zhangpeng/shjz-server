package com.shjz.zp95sky.shjz.server.common.interceptor;

import cn.hutool.core.lang.Snowflake;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 针对日志打印 traceId
 * @author 华夏紫穹
 * @date 2021年06月08日 13:39
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class LogInterceptor implements HandlerInterceptor {

    @Resource
    private final Snowflake snowflake;

    private final static String TRACE_ID = "traceId";

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        ThreadContext.put("traceId", snowflake.nextIdStr());
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        ThreadContext. remove(TRACE_ID);
    }

}
