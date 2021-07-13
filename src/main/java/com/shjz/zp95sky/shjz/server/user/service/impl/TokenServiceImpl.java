package com.shjz.zp95sky.shjz.server.user.service.impl;

import com.shjz.zp95sky.shjz.server.common.constants.Constants;
import com.shjz.zp95sky.shjz.server.common.jwt.JwtTokenProvider;
import com.shjz.zp95sky.shjz.server.user.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 华夏紫穹
 * @date 2021年04月08日 18:14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class TokenServiceImpl implements TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final HttpServletRequest request;

    @Override
    public String getTokenByHeaderStr(String jwtStr) {
        int jwtTokenPreLength = Constants.JWT_TOKEN_PRE.length();
        if (StringUtils.hasLength(jwtStr) || !jwtStr.startsWith(Constants.JWT_TOKEN_PRE) || jwtStr.length() <= jwtTokenPreLength) {
            return null;
        }
        return jwtStr.substring(jwtTokenPreLength);
    }

    @Override
    public Long getUserIdByToken(String token) {
        Claims claims = jwtTokenProvider.parseToken(token);
        return Long.parseLong(claims.getAudience());
    }

    @Override
    public Long getUserIdByHeaderStr(String jwtStr) {
        String token = getTokenByHeaderStr(jwtStr);
        if (StringUtils.hasLength(token)) { return null; }
        return getUserIdByToken(token);
    }

    @Override
    public String getCurrentOperatorToken() {
        String tokenStr = request.getHeader(Constants.JWT_HEADER_NAME);
        return getTokenByHeaderStr(tokenStr);
    }

}
