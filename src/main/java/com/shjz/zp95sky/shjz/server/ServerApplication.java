package com.shjz.zp95sky.shjz.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
