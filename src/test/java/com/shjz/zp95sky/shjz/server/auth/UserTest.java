package com.shjz.zp95sky.shjz.server.auth;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.user.utils.EncryptUtil;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.mapper.UserMapper;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * 用户测试类
 */
@CommonTestAnnotation
public class UserTest {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Test
    public void testCreateAccount() {
        User user = userService.getUser();
        String password = EncryptUtil.passwordEncrypt("123456");

        User updateUser = User.builder().id(user.getId()).password(password).build();
        userMapper.updateById(updateUser);
    }

}
