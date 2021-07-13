package com.shjz.zp95sky.shjz.server.user.service;

/**
 * token 处理
 * @author 华夏紫穹
 * @date 2021年04月08日 18:12
 */
public interface TokenService {

    /**
     * 从 token 头中解析 token 字符串
     * @param jwtStr token 头内容
     * @return token 字符串
     */
    String getTokenByHeaderStr(String jwtStr);

    /**
     * 根据 token 获取用户 ID
     * @param token token 字符串
     * @return token 用户 ID
     */
    Long getUserIdByToken(String token);

    /**
     * 从 token 头中解析用户 ID
     * @param jwtStr token 头内容
     * @return 用户 ID
     */
    Long getUserIdByHeaderStr(String jwtStr);

    /**
     * 获取当前登录用户的 token
     * @return 当前登录用户的 token
     */
    String getCurrentOperatorToken();

}
