/**
 * 
 */
package com.bo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月17日 下午11:05:11
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月17日      yangbo          v1.0.0             版本创建
 */
@Configuration
@EnableWebSecurity
public class OAuthWebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("123").roles("USER")
			.and()
			.withUser("admin").password("123").roles("ADMIN");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/oauth/authorize").authenticated()
			.and()
			.httpBasic().realmName("OAuth server");
	}
}
