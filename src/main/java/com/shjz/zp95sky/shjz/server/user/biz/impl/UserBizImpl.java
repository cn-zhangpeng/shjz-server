package com.shjz.zp95sky.shjz.server.user.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import com.shjz.zp95sky.shjz.server.user.biz.LoginBiz;
import com.shjz.zp95sky.shjz.server.user.biz.UserBiz;
import com.shjz.zp95sky.shjz.server.user.domain.UserAllDo;
import com.shjz.zp95sky.shjz.server.user.domain.UserDo;
import com.shjz.zp95sky.shjz.server.user.dto.ResetPasswordDto;
import com.shjz.zp95sky.shjz.server.user.dto.UserAllDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import com.shjz.zp95sky.shjz.server.user.utils.EncryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class UserBizImpl implements UserBiz {

    private final LoginBiz loginBiz;

    private final UserService userService;

    @Override
    public BaseResult<UserDo> getUserInfo() {
        User user = getLatestUserInfo();
        UserDo userDo = handleUserData(user);
        return ResultUtil.buildResultSuccess(userDo);
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
        return updateUserById(newUser);
    }

    @Override
    public BaseResult<Void> resetPassword(ResetPasswordDto passwordDto) {
        User curUser = loginBiz.getLoginUser();

        ResponseCodeEnum errorCodeEnum = resetPasswordCheck(curUser, passwordDto);
        if (errorCodeEnum != null) {
            return ResultUtil.buildResultError(errorCodeEnum);
        }

        User newUser = User.builder().id(curUser.getId())
                .password(EncryptUtil.passwordEncrypt(passwordDto.getNewPassword()))
                .build();
        updateUserById(newUser);
        return ResultUtil.buildResultSuccess();
    }

    public ResponseCodeEnum resetPasswordCheck(User curUser, ResetPasswordDto passwordDto) {
        String curPassword = passwordDto.getCurrentPassword();
        String newPassword = passwordDto.getNewPassword();

        if (StringUtils.hasLength(curPassword) || StringUtils.hasLength(newPassword)) {
            return ResponseCodeEnum.ERROR_PARAMS;
        }

        User user = getUser();
        if (ObjectUtils.isEmpty(curUser) || ObjectUtils.isEmpty(user) ||
                ObjectUtils.isEmpty(curUser.getId()) || !curUser.getId().equals(user.getId())) {
            return ResponseCodeEnum.ERROR_USER_NOT_EXIST;
        }

        // 当前密码不匹配
        if (!EncryptUtil.passwordEncrypt(curPassword).equals(user.getPassword())) {
            return ResponseCodeEnum.ERROR_CURRENT_PASSWORD_MOT_MATCH;
        }

        return null;
    }

    private User getLatestUserInfo() {
        List<User> userList = getAllUser();
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

    private List<User> getAllUser() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(User::getCreateTime);
        return userService.list(queryWrapper);
    }

    private boolean updateUserById(User user) {
        return userService.updateById(user);
    }

}
