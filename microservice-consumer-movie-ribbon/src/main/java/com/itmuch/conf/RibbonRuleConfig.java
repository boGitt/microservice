/**
 * 
 */
package com.itmuch.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年2月22日 下午11:14:03
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年2月22日      yangbo          v1.0.0             版本创建
 */
@Configuration
public class RibbonRuleConfig {

	/*
	 * RibbonRuleConfig 不在ComponentSacn包下时可自动装载  IClientConfig并实现入参ribbonRule方法
	 * 					   在ComponentSacn包下时 自动装载IClientConfig会报错，可通过实现无入参的ribbonRule方法实现自定义规则配置
	 * 综上，直接使用无入参的ribbonRule实现自定义客户端负载均衡策略(不考虑复杂情况)
	 * 
	@Autowired
	private IClientConfig config;
	
	@Bean
	public IRule ribbonRule(IClientConfig config){
		return new RandomRule();
	}
	*/
	
	@Bean
	public IRule ribbonRule(){
		return new RandomRule();
	}
	
}
