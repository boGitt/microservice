/**
 * 
 */
package com.itmuch.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年3月7日 下午11:13:59
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年3月7日      yangbo          v1.0.0             版本创建
 */
@RestController
public class ConfigClientController {

	@Value("${profile}")
	private String profile;
	
	@GetMapping("/profile")
	public String getProfile(){
		return this.profile;
	}
	
}
