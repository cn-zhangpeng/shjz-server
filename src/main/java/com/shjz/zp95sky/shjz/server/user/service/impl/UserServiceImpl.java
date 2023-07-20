package com.shjz.zp95sky.shjz.server.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.mapper.UserMapper;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username)
                .orderByDesc(User::getCreateTime);
        List<User> userList = list(queryWrapper);

        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

}
