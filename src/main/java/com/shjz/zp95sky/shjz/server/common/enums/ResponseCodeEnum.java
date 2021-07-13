package com.shjz.zp95sky.shjz.server.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码
 * @author 华夏紫穹
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    /**
     * 用户相关
     */
    ERROR_AUTH_INVALID(10001, "认证失败！"),
    ERROR_USERNAME_OR_PASSWORD(10002, "用户名或密码错误！"),
    ERROR_USER_NOT_EXIST(10003, "用户不存在！"),
    ERROR_CURRENT_PASSWORD_MOT_MATCH(10004, "当前密码错误！"),

    /**
     * 常用错误码
     */
    SUCCESS(200, "成功！"),
    ERROR_PARAMS(400, "参数错误！"),
    ERROR_SYSTEM_ERROR(500, "发生未知错误，请联系管理员！");

    private final Integer code;
    private final String msg;

}
