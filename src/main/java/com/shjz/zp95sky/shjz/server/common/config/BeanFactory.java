package com.shjz.zp95sky.shjz.server.common.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.shjz.zp95sky.shjz.server.common.utils.RedisUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean 工厂
 *
 * @author 华夏紫穹
 * @date 2021年03月25日 11:15
 */
@Configuration
public class BeanFactory {

    @Value("${id-worker.worker-id}")
    private long workerId;

    @Value("${id-worker.data-center-id}")
    private long dataCenterId;

    @Bean
    public Snowflake idWorker() {
        return IdUtil.getSnowflake(workerId, dataCenterId);
    }

    @Bean
    public RedisUtil redisUtil(RedissonClient redissonClient) {
        return new RedisUtil(redissonClient);
    }

}
