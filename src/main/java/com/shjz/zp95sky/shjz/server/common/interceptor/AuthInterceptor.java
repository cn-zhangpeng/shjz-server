package com.shjz.zp95sky.shjz.server.common.interceptor;

import cn.hutool.core.text.AntPathMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shjz.zp95sky.shjz.server.common.constants.Constants;
import com.shjz.zp95sky.shjz.server.common.constants.RedisConstants;
import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.common.utils.CustomRedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 针对日志打印 traceId
 * @author 山海紫穹
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class AuthInterceptor implements HandlerInterceptor {

    private final CustomRedisUtil redisUtil;
    private final ObjectMapper objectMapper;

    @Value("${auth.ignore-urls}")
    private List<String> ignoreUrls;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {

        // 白名单，直接放行
        String path = request.getRequestURI().replace(request.getContextPath(), Constants.STRING_DATA_DEFAULT);
        if (checkUrls(ignoreUrls, path)) {
            return true;
        }

        String token = request.getHeader(Constants.TOKEN_HEADER_NAME);
        boolean existToken = redisUtil.exists(RedisConstants.OPERATOR_AUTH_TOKEN_PRE + token);
        if (existToken) {
            return true;
        }

        permissionDenied(response);
        return false;
    }

    private boolean checkUrls(List<String> urls, String path) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String url : urls) {
            if (pathMatcher.match(url, path)) {
                return true;
            }
        }
        return false;
    }

    private void permissionDenied(HttpServletResponse response) throws IOException {
        BaseResult<Void> result = ResultUtil.buildResultError(ResponseCodeEnum.PERMISSION_DENIED);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

}
