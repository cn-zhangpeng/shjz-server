package com.shjz.zp95sky.shjz.server.user.service;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.user.dto.LoginDto;

/**
 * 登录逻辑检测
 * @author 山海紫穹
 * @date 2021年03月25日 18:24
 */
public interface LoginCheckService {

    /**
     * 登录检测
     * @param loginDto 登录参数
     * @return 检测结果，检测成功，返回 null，否则返回错误信息
     */
    ResponseCodeEnum loginCheck(LoginDto loginDto);

}
