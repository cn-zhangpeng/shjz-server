package com.shjz.zp95sky.shjz.server.common.jwt;


import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

/**
 * jwt 配置
 * @author 华夏紫穹
 */
@Data
@Component
@ConfigurationProperties("token.jwt")
public class JwtConfiguration {


    /** 计算 token 用的 key */
    private String key;

    /** 在哪里生成的这个token */
    private String iss;

    /** 有效期：分钟 */
    private int expm;

    public SecretKeySpec getSecretKeySpec() {
        return new SecretKeySpec(this.getKey()
                .getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }
}
