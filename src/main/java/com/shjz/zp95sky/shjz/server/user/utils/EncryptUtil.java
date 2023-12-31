package com.shjz.zp95sky.shjz.server.user.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 加密工具类
 * @author 山海紫穹
 * @date 2021年04月08日 17:59
 */
public class EncryptUtil {

    private EncryptUtil() { }

    /**
     * 密码加密算法
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String passwordEncrypt(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

}
