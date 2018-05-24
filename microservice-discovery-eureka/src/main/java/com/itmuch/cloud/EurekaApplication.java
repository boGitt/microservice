/**
 * 
 */
package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年2月18日 下午4:26:53
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年2月18日      yangbo          v1.0.0             版本创建
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(EurekaApplication.class, args);
	}

}
