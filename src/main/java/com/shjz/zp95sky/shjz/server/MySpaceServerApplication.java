package com.shjz.zp95sky.shjz.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口
 * @author 华夏紫穹
 */
@SpringBootApplication
@MapperScan("com.myspace.server.*.mapper")
public class MySpaceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpaceServerApplication.class, args);
	}

}
