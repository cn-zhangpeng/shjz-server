package com.shjz.zp95sky.shjz.server.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisExpireListener extends KeyExpirationEventMessageListener {

    public RedisExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    //当消息过期，触发方法
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        log.info("Key -> {} 过期了...", expiredKey);
    }

}
