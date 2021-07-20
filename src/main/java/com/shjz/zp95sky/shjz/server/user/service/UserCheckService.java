package com.shjz.zp95sky.shjz.server.user.service;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.user.dto.ResetPasswordDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;

/**
 * 用户业务处理检测接口
 * @author 山海紫穹
 */
public interface UserCheckService {

    /**
     * 重置密码检测
     * @param curUser 当前登录用户
     * @param passwordDto 密码 参数
     * @return 检测通过，返回 null；检测通过，返回错误信息对象
     */
    ResponseCodeEnum resetPasswordCheck(User curUser, ResetPasswordDto passwordDto);

}
