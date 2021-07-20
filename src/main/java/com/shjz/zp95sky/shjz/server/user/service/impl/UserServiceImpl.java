package com.shjz.zp95sky.shjz.server.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shjz.zp95sky.shjz.server.user.utils.EncryptUtil;
import com.shjz.zp95sky.shjz.server.user.domain.UserAllDo;
import com.shjz.zp95sky.shjz.server.user.domain.UserDo;
import com.shjz.zp95sky.shjz.server.user.dto.ResetPasswordDto;
import com.shjz.zp95sky.shjz.server.user.dto.UserAllDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.mapper.UserMapper;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户业务实现
 * @author 山海紫穹
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserDo getUserInfo() {
        User user = getLatestUserInfo();
        return handleUserData(user);
    }

    @Override
    public User getUser() {
        return getLatestUserInfo();
    }

    @Override
    public UserAllDo getUserAllInfo() {
        User user = getLatestUserInfo();
        return handleUserAllData(user);
    }

    @Override
    public boolean updateUserInfo(UserAllDto userDto) {
        User user = getLatestUserInfo();
        if (user == null || user.getId() == null) { return false; }

        User newUser = handleUser(user.getId(), userDto);
        return updateUserById(newUser) >= 1;
    }

    @Override
    public boolean resetPassword(Long userId, ResetPasswordDto passwordDto) {
        User newUser = User.builder().id(userId)
                .password(EncryptUtil.passwordEncrypt(passwordDto.getNewPassword()))
                .build();
        return updateUserById(newUser) == 1;
    }

    private User getLatestUserInfo() {
        List<User> userList = selectAllUser();
        if (CollectionUtils.isEmpty(userList)) { return null; }
        return userList.get(0);
    }

    private User handleUser(Long userId, UserAllDto user) {
        if (user == null) { return null; }

        return User.builder()
                .id(userId).nickname(user.getNickname()).sex(user.getSex())
                .birthday(user.getBirthday()).avatar(user.getAvatar()).maxim(user.getMaxim())
                .phone(user.getPhone()).email(user.getEmail()).qq(user.getQq())
                .location(user.getLocation())
                .build();
    }

    private UserDo handleUserData(User user) {
        if (user == null) { return null; }

        return UserDo.builder()
                .username(user.getUsername()).nickname(user.getNickname())
                .avatar(user.getAvatar()).maxim(user.getMaxim())
                .build();
    }

    private UserAllDo handleUserAllData(User user) {
        if (user == null) { return null; }

        return UserAllDo.builder()
                .nickname(user.getNickname()).sex(user.getSex()).birthday(user.getBirthday())
                .avatar(user.getAvatar()).maxim(user.getMaxim()).phone(user.getPhone())
                .email(user.getEmail()).qq(user.getQq()).location(user.getLocation())
                .build();
    }

    private List<User> selectAllUser() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(User::getCreateTime);
        return userMapper.selectList(queryWrapper);
    }

    private int updateUserById(User user) {
        return userMapper.updateById(user);
    }

}
