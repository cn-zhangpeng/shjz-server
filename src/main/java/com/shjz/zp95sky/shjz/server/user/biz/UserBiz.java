package com.shjz.zp95sky.shjz.server.user.biz;

import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.user.domain.UserAllDo;
import com.shjz.zp95sky.shjz.server.user.domain.UserDo;
import com.shjz.zp95sky.shjz.server.user.dto.ResetPasswordDto;
import com.shjz.zp95sky.shjz.server.user.dto.UserAllDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;

/**
 * 用户业务处理
 */
public interface UserBiz {

    /**
     * 查询用户信息
     * @return 用户信息
     */
    BaseResult<UserDo> getUserInfo();

    /**
     * 查询用户原始信息
     * @return {@link User} 用户信息
     */
    User getUser();

    /**
     * 查询用户全部信息
     * @return {@link UserAllDo} 用户全部信息
     */
    UserAllDo getUserAllInfo();

    /**
     * 更新用户信息
     * @param userDto 用户信息
     * @return 更新成功，返回 true，否则返回 false
     */
    boolean updateUserInfo(UserAllDto userDto);

    /**
     * 更新登录密码
     * @param passwordDto 用户密码信息
     * @return 更新结果
     */
    BaseResult<Void> resetPassword(ResetPasswordDto passwordDto);

}
