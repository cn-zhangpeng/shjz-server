package com.shjz.zp95sky.shjz.server.user.service.impl;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.user.utils.EncryptUtil;
import com.shjz.zp95sky.shjz.server.user.dto.ResetPasswordDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.service.UserCheckService;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 用户业务处理检测实现
 * @author 山海紫穹
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class UserCheckServiceImpl implements UserCheckService {

    private final UserService userService;

    @Override
    public ResponseCodeEnum resetPasswordCheck(User curUser, ResetPasswordDto passwordDto) {
        String curPassword = passwordDto.getCurrentPassword();
        String newPassword = passwordDto.getNewPassword();

        if (StringUtils.hasLength(curPassword) || StringUtils.hasLength(newPassword)) {
            return ResponseCodeEnum.ERROR_PARAMS;
        }

        User user = userService.getUser();
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

}
