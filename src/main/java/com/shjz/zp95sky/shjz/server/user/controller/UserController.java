package com.shjz.zp95sky.shjz.server.user.controller;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.user.biz.UserBiz;
import com.shjz.zp95sky.shjz.server.user.domain.UserAllDo;
import com.shjz.zp95sky.shjz.server.user.domain.UserDo;
import com.shjz.zp95sky.shjz.server.user.dto.ResetPasswordDto;
import com.shjz.zp95sky.shjz.server.user.dto.UserAllDto;
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

    private final UserBiz userBiz;

    @ApiOperation("查询用户信息")
    @GetMapping("/info")
    public BaseResult<UserDo> getUserInfo() {
        return userBiz.getUserInfo();
    }

    @ApiOperation("查询用户全部信息")
    @GetMapping("/allInfo")
    public BaseResult<UserAllDo> getUserAllInfo() {
        UserAllDo userDo = userBiz.getUserAllInfo();
        return ResultUtil.buildResultSuccess(userDo);
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/info")
    public BaseResult<Void> updateUserInfo(@RequestBody UserAllDto userDto) {
        boolean result = userBiz.updateUserInfo(userDto);
        return result ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    @ApiOperation("密码重置")
    @PatchMapping("/password/reset")
    public BaseResult<Void> resetPassword(@RequestBody ResetPasswordDto passwordDto) {
        return userBiz.resetPassword(passwordDto);
    }

}
