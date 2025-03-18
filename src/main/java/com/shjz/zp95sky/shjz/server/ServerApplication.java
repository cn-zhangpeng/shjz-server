package com.shjz.zp95sky.shjz.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author 山海紫穹
 * @date 2021年06月22日 13:30
 */
@SpringBootApplication
@MapperScan("com.shjz.zp95sky.shjz.server.*.mapper")
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
		//Redis消息监听器
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		//设置Redis链接工厂
		container.setConnectionFactory(connectionFactory);

		return container;
	}

}
