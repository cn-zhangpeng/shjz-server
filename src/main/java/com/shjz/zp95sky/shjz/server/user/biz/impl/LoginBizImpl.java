package com.shjz.zp95sky.shjz.server.user.biz.impl;

import cn.hutool.core.lang.Snowflake;
import com.shjz.zp95sky.shjz.server.common.constants.Constants;
import com.shjz.zp95sky.shjz.server.common.constants.RedisConstants;
import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.common.utils.CustomRedisUtil;
import com.shjz.zp95sky.shjz.server.user.biz.LoginBiz;
import com.shjz.zp95sky.shjz.server.user.domain.LoginDo;
import com.shjz.zp95sky.shjz.server.user.dto.LoginDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import com.shjz.zp95sky.shjz.server.user.utils.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class LoginBizImpl implements LoginBiz {

    private final Snowflake snowflake;
    private final CustomRedisUtil redisUtil;

    private final UserService userService;

    private final HttpServletRequest request;

    @Value("${token.expire}")
    private Integer tokenExpire;

    @Override
    public BaseResult<LoginDo> login(LoginDto loginDto) {
        ResponseCodeEnum error = loginCheck(loginDto);
        if (error != null) {
            return ResultUtil.buildResultError(error);
        }

        // 查询用户
        User user = userService.getByUsername(loginDto.getUsername());

        // 登录成功，生成 token
        String token = snowflake.nextIdStr();

        // redis 保存登录信息（一天）
        saveAuthTokenToRedis(token, user);

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
        return getUserByAuthToken(token);
    }

    public ResponseCodeEnum loginCheck(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return ResponseCodeEnum.ERROR_PARAMS;
        }

        // 检查用户名和密码
        User user = userService.getByUsername(username);
        if (user == null || !EncryptUtil.passwordEncrypt(password).equals(user.getPassword())) {
            return ResponseCodeEnum.ERROR_USERNAME_OR_PASSWORD;
        }

        return null;
    }

    private String getLoginUserToken() {
        return request.getHeader(Constants.TOKEN_HEADER_NAME);
    }

    private void saveAuthTokenToRedis(String token, User user) {
        redisUtil.set(RedisConstants.OPERATOR_AUTH_TOKEN_PRE + token, user, tokenExpire, TimeUnit.MINUTES);
    }

    private User getUserByAuthToken(String token) {
        return redisUtil.get(RedisConstants.OPERATOR_AUTH_TOKEN_PRE + token);
    }

    private void removeAuthTokenFromRedis(String token) {
        redisUtil.del(RedisConstants.OPERATOR_AUTH_TOKEN_PRE + token);
    }

}
