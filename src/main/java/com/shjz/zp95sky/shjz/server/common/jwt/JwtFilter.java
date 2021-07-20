package com.shjz.zp95sky.shjz.server.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shjz.zp95sky.shjz.server.common.constants.Constants;
import com.shjz.zp95sky.shjz.server.common.constants.RedisConstants;
import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.common.utils.RedisUtil;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.service.TokenService;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * Jwt 过滤器
 * @author zhangpeng
 * @date 2021年03月26日 3:00
 */
@WebFilter(urlPatterns = "/*")
//@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class JwtFilter implements Filter {

    private final String CONTEXT_PATH = "/shjz";

    /** 不需要登录认证的接口 */
    private final List<String> WHITE_LIST = Collections.singletonList(
            CONTEXT_PATH + "/login"
    );

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtil redisUtil;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // Get 请求无需校验
        String requestMethod = httpServletRequest.getMethod();
        if (HttpMethod.GET.name().equals(requestMethod)) {
            chain.doFilter(request, response);
            return;
        }

        // 无需校验
        if (WHITE_LIST.contains(httpServletRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        // token 格式校验
        String jwtStr = httpServletRequest.getHeader(Constants.JWT_HEADER_NAME);
        int jwtTokenPreLength = Constants.JWT_TOKEN_PRE.length();
        if (StringUtils.hasLength(jwtStr) || !jwtStr.startsWith(Constants.JWT_TOKEN_PRE) || jwtStr.length() <= jwtTokenPreLength) {
            authFailed(httpServletResponse);
            return;
        }

        // 登录校验
        String token = tokenService.getTokenByHeaderStr(jwtStr);
        if (!isAuthSuccess(token)) {
            authFailed(httpServletResponse);
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthSuccess(String token) {
        if (!isTokenValid(token)) {
            return false;
        }

        // 从 redis 校验 token
        Object res = redisUtil.get(RedisConstants.OPERATOR_AUTH_TOKEN_PRE + token);
        if (!(res instanceof String)) {
            return false;
        }

        return userIsExist();
    }

    private boolean isTokenValid(String token) {
        return jwtTokenProvider.parseToken(token) != null;
    }

    private boolean userIsExist() {
        User user = userService.getUser();
        return user != null;
    }

    private void authFailed(HttpServletResponse response) {
        BaseResult<Void> res = ResultUtil.buildResultError(ResponseCodeEnum.ERROR_AUTH_INVALID);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (PrintWriter writer = response.getWriter()) {
            String resStr = objectMapper.writeValueAsString(res);
            writer.print(resStr);
        } catch (IOException e) {
            log.error("response error", e);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

}
