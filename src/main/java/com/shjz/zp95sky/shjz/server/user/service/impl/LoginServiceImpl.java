package com.shjz.zp95sky.shjz.server.user.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import com.shjz.zp95sky.shjz.server.common.constants.Constants;
import com.shjz.zp95sky.shjz.server.common.constants.RedisConstants;
import com.shjz.zp95sky.shjz.server.auth.jwt.JwtConfiguration;
import com.shjz.zp95sky.shjz.server.auth.jwt.JwtTokenProvider;
import com.shjz.zp95sky.shjz.server.auth.jwt.UserAuthClaims;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.common.utils.RedisUtil;
import com.shjz.zp95sky.shjz.server.user.domain.LoginDo;
import com.shjz.zp95sky.shjz.server.user.dto.LoginDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.service.LoginService;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 登录实现逻辑
 * @author 山海紫穹
 * @date 2021年03月25日 18:24
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class LoginServiceImpl implements LoginService {

    private final Snowflake snowflake;
    private final RedisUtil redisUtil;
    private final JwtConfiguration jwtConfiguration;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final HttpServletRequest request;

    @Override
    public BaseResult<LoginDo> login(LoginDto loginDto) {
        // 查询当前用户
        log.info("search user");
        User user = userService.getUser();

        // 登录成功，生成 token
        log.info("create token");
        String token = createToken(user);

        // redis 保存登录信息（一天）
        log.info("save token to redis");
        saveAuthTokenToRedis(user.getId(), token);

        // 响应信息
        LoginDo loginDo = LoginDo.builder()
                .username(user.getUsername())
                .accessToken(token)
                .build();
        return ResultUtil.buildResultSuccess(loginDo);
    }

    @Override
    public BaseResult<Void> logout() {
        String token = getLoginUserToken();

        // redis 删除 token 信息
        removeAuthTokenFromRedis(token);

        return ResultUtil.buildResultSuccess();
    }

    @Override
    public User getLoginUser() {
        String token = getLoginUserToken();
        Claims claims = jwtTokenProvider.parseToken(token);
        return userService.getById(claims.getAudience());
    }

    private String getLoginUserToken() {
        return request.getHeader(Constants.JWT_HEADER_NAME);
    }

    private String createToken(User user) {
        UserAuthClaims uaClaims = new UserAuthClaims();
        Date curDate = new Date();
        uaClaims.setIssuer(jwtConfiguration.getIss());
        uaClaims.setIssuedAt(curDate);
        uaClaims.setAudience(String.valueOf(user.getId()));
        uaClaims.setId(snowflake.nextIdStr());
        uaClaims.setExpiration(DateUtil.offsetMinute(curDate, jwtConfiguration.getExpm()));
        uaClaims.setUserId(String.valueOf(user.getId()));
        uaClaims.setUsername(user.getUsername());
        uaClaims.setSubject(String.valueOf(user.getId()));
        uaClaims.setNotBefore(curDate);
        return jwtTokenProvider.createToken(uaClaims);
    }

    private void saveAuthTokenToRedis(Long userId, String token) {
        redisUtil.set(RedisConstants.OPERATOR_AUTH_TOKEN_PRE + token, userId, 1, TimeUnit.DAYS);
    }

    private void removeAuthTokenFromRedis(String token) {
        redisUtil.del(RedisConstants.OPERATOR_AUTH_TOKEN_PRE + token);
    }

}


