package com.shjz.zp95sky.shjz.server.user.service.impl;

import com.shjz.zp95sky.shjz.server.common.enums.ResponseCodeEnum;
import com.shjz.zp95sky.shjz.server.user.utils.EncryptUtil;
import com.shjz.zp95sky.shjz.server.user.dto.LoginDto;
import com.shjz.zp95sky.shjz.server.user.entity.User;
import com.shjz.zp95sky.shjz.server.user.service.LoginCheckService;
import com.shjz.zp95sky.shjz.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 山海紫穹
 * @date 2021年03月25日 18:29
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class LoginCheckServiceImpl implements LoginCheckService {

    private final UserService userService;

    @Override
    public ResponseCodeEnum loginCheck(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return ResponseCodeEnum.ERROR_PARAMS;
        }

        // 检查用户名和密码
        User user = userService.getUser();
        if (user == null || !EncryptUtil.passwordEncrypt(password).equals(user.getPassword())) {
            return ResponseCodeEnum.ERROR_USERNAME_OR_PASSWORD;
        }

        return null;
    }

}
