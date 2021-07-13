package com.shjz.zp95sky.shjz.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口
 * @author 华夏紫穹
 */
@SpringBootApplication
@MapperScan("com.shjz.zp95sky.shjz.server.*.mapper")
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
