package com.bo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * 
 */

/**   
 * Copyright: Copyright (c) 2018 bobo
 * 
 * @ClassName: OAuth20ClientApplication.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月23日 上午11:09:43 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月23日      yangbo          v1.0.0             版本创建
 */
@EnableOAuth2Sso
@SpringBootApplication
public class OAuth20ClientApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(OAuth20ClientApplication.class,args);
	}

}
