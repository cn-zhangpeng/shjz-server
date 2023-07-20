package com.shjz.zp95sky.shjz.server.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shjz.zp95sky.shjz.server.user.entity.User;

/**
 * 用户数据处理
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User getByUsername(String username);

}
