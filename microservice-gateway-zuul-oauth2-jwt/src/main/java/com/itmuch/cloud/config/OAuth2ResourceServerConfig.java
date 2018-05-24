/**
 * 
 */
package com.itmuch.cloud.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.alibaba.druid.pool.DruidDataSource;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月22日 上午11:00:28
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月22日      yangbo          v1.0.0             版本创建
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DruidProperties druidProperties;
    
	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
	}
	
	@Bean
	@Primary
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource());
	}
	
	@Bean
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(jwtTokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer config) {
		config.tokenServices(tokenServices());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/oauth2jwt/**").permitAll()
			.antMatchers(HttpMethod.GET,"/**").access("#oauth2.hasScope('read')")
			.antMatchers(HttpMethod.POST,"/**").access("#oauth2.hasScope('write')");
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {  
	    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();  
	    Resource resource = new ClassPathResource("public.txt");  
	    String publicKey = null;  
	    try {  
	        publicKey = org.apache.commons.io.IOUtils.toString(resource.getInputStream());  
	    } catch (final IOException e) {  
	        throw new RuntimeException(e);  
	    }  
	    converter.setVerifierKey(publicKey);  
	    return converter;  
	}  
	
	@Bean  
	public TokenStore jwtTokenStore() {  
	    return new JwtTokenStore(accessTokenConverter());  
	} 
	
	private String convertStreamToString(InputStream is) {      
        /*  
          * To convert the InputStream to String we use the BufferedReader.readLine()  
          * method. We iterate until the BufferedReader return null which means  
          * there's no more data to read. Each line will appended to a StringBuilder  
          * and returned as String.  
          */     
         BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
         StringBuilder sb = new StringBuilder();      
     
         String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {      
                 sb.append(line + "\n");      
             }      
         } catch (IOException e) {      
             e.printStackTrace();      
         } finally {      
            try {      
                 is.close();      
             } catch (IOException e) {      
                 e.printStackTrace();      
             }      
         }      
     
        return sb.toString();      
     }
}
