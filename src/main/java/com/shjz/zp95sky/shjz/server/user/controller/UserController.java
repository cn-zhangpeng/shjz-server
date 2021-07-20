package com.shjz.zp95sky.shjz.server.user.controller;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.user.domain.UserAllDo;
import com.shjz.zp95sky.shjz.server.user.domain.UserDo;
import com.shjz.zp95sky.shjz.server.user.dto.ResetPasswordDto;
import com.shjz.zp95sky.shjz.server.user.dto.UserAllDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.service.LoginService;
import com.shjz.zp95sky.shjz.server.user.service.UserCheckService;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * @author 山海紫穹
 */
@Api(value = "用户", tags = "用户接口")
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class UserController {

    private final UserService userService;
    private final UserCheckService userCheckService;
    private final LoginService loginService;

    @ApiOperation("查询用户信息")
    @GetMapping("/info")
    public BaseResult<UserDo> getUserInfo() {
        UserDo userDo = userService.getUserInfo();
        return ResultUtil.buildResultSuccess(userDo);
    }

    @ApiOperation("查询用户全部信息")
    @GetMapping("/allInfo")
    public BaseResult<UserAllDo> getUserAllInfo() {
        UserAllDo userDo = userService.getUserAllInfo();
        return ResultUtil.buildResultSuccess(userDo);
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/info")
    public BaseResult<Void> updateUserInfo(@RequestBody UserAllDto userDto) {
        boolean result = userService.updateUserInfo(userDto);
        return result ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    @ApiOperation("密码重置")
    @PatchMapping("/password/reset")
    public BaseResult<Void> resetPassword(@RequestBody ResetPasswordDto passwordDto) {
        User user = loginService.getLoginUser();

        ResponseCodeEnum error = userCheckService.resetPasswordCheck(user, passwordDto);
        if (error != null) {
            return ResultUtil.buildResultError(error);
        }

        boolean result = userService.resetPassword(user.getId(), passwordDto);
        return result ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

}
