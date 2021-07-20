package com.shjz.zp95sky.shjz.server.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * jwt 工具类
 * @author zhangpeng
 */
@Component
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class JwtTokenProvider {

	private final ObjectMapper objectMapper;

	private final JwtConfiguration configuration;

	/**
	 * 生成token
	 */
	public String createToken(Claims claims) {
		try {
			return Jwts.builder().setPayload(objectMapper.writeValueAsString(claims))
					.compressWith(CompressionCodecs.DEFLATE)
					.signWith(SignatureAlgorithm.HS512, configuration.getSecretKeySpec()).compact();
		} catch (Exception e) {
			log.error("createToken error!", e);
		}

		return null;
	}

	/**
	 * token转换
	 */
	public Claims parseToken(String token) {
		try {
			return Jwts.parser().setSigningKey(configuration.getSecretKeySpec()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
