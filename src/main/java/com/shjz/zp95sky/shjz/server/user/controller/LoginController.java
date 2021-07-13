package com.shjz.zp95sky.shjz.server.user.controller;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ModelResultUtil;
import com.shjz.zp95sky.shjz.server.user.domain.LoginDo;
import com.shjz.zp95sky.shjz.server.user.dto.LoginDto;
import com.shjz.zp95sky.shjz.server.user.service.LoginCheckService;
import com.shjz.zp95sky.shjz.server.user.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 * @author 华夏紫穹
 * @date 2021年04月08日 17:09
 */
@Api(value = "登录", tags = "登录接口")
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class LoginController {

    private final LoginCheckService loginCheckService;
    private final LoginService loginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResult<LoginDo> login(@RequestBody LoginDto loginDto) {
        log.info("start to login...");
        ResponseCodeEnum error = loginCheckService.loginCheck(loginDto);
        if (error != null) {
            return ModelResultUtil.buildResultError(error);
        }
        return loginService.login(loginDto);
    }

}
