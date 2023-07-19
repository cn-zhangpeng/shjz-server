package com.shjz.zp95sky.shjz.server.common.config;

import com.shjz.zp95sky.shjz.server.common.utils.RedisUtil;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 山海紫穹
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisUtil redisUtil(RedissonClient redissonClient) {
        return new RedisUtil(redissonClient);
    }

}
