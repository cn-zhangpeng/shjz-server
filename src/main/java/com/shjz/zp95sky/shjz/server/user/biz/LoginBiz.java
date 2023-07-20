package com.shjz.zp95sky.shjz.server.user.biz;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.user.domain.LoginDo;
import com.shjz.zp95sky.shjz.server.user.dto.LoginDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;

/**
 * 登录认证业务处理
 */
public interface LoginBiz {

    /**
     * 登录请求
     * @author zhangpeng
     * @param loginDto 登录参数
     * @return 用户信息
     */
    BaseResult<LoginDo> login(LoginDto loginDto);

    /**
     * 登录请求
     * @author zhangpeng
     * @return 用户信息
     */
    BaseResult<Void> logout();

    /**
     * 获取当前登录用户
     * @return 当前登录用户信息
     */
    User getLoginUser();

}
