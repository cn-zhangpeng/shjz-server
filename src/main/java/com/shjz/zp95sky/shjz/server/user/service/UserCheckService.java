package com.shjz.zp95sky.shjz.server.user.service;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.user.dto.ResetPasswordDto;

/**
 * 用户业务处理检测接口
 * @author 华夏紫穹
 */
public interface UserCheckService {

    /**
     * 重置密码检测
     * @param userId 用户 ID
     * @param passwordDto 密码 参数
     * @return 检测通过，返回 null；检测通过，返回错误信息对象
     */
    ResponseCodeEnum resetPasswordCheck(Long userId, ResetPasswordDto passwordDto);

}
