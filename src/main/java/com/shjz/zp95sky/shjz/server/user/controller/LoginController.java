package com.shjz.zp95sky.shjz.server.user.controller;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.user.biz.LoginBiz;
import com.shjz.zp95sky.shjz.server.user.domain.LoginDo;
import com.shjz.zp95sky.shjz.server.user.dto.LoginDto;
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
 */
@Api(value = "登录", tags = "登录接口")
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class LoginController {

    private final LoginBiz loginBiz;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResult<LoginDo> login(@RequestBody LoginDto loginDto) {
        return loginBiz.login(loginDto);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public BaseResult<Void> logout() {
        return loginBiz.logout();
    }

}
