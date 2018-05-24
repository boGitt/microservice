/**
 * 
 */
package com.itmuch.cloud.config;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月17日 下午10:13:31
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月17日      yangbo          v1.0.0             版本创建
 */
@Configuration
@EnableAuthorizationServer
public class OAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	/*
	@Value("${oauth.client_id}")
	private String OAUTH_CLIENT_ID; 
	
	@Value("${oauth.client_secret}")
	private String OAUTH_CLIENT_SECRET;
	
	@Value("${oauth.resource_id}")
	private String RESOURCE_ID;
	
	@Value("${oauth.scopes}")
	private String[] SCOPES;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory()
				.withClient(OAUTH_CLIENT_ID)
				.secret(OAUTH_CLIENT_SECRET)
				.resourceIds(RESOURCE_ID)
				.scopes(SCOPES)
				.authorities("ROLE_USER")
				.authorizedGrantTypes("authorization_code","refresh_token")
				.redirectUris("http://default-oauth-callback.com")
				.accessTokenValiditySeconds(60*5)
				.refreshTokenValiditySeconds(60*60*24);
		
	}
	*/

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.allowFormAuthenticationForClients();
	}
	
	@Autowired
	private DataSource dataSource;
	
	@Bean  
    public ApprovalStore approvalStore() {  
        return new JdbcApprovalStore(dataSource);  
    }  
    @Bean  
    protected AuthorizationCodeServices authorizationCodeServices() {  
        return new JdbcAuthorizationCodeServices(dataSource);  
    }  
    @Bean  
    public TokenStore tokenStore() {  
        return new JdbcTokenStore(dataSource);  
    }  
      
    @Override  
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {  
        clients.jdbc(dataSource); // oauth_client_details  
    }  
      
    @Override  
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {  
        endpoints.approvalStore(approvalStore())                            // oauth_approvals  
                 .authorizationCodeServices(authorizationCodeServices())    // oauth_code  
                 .tokenStore(tokenStore())                                 // oauth_access_token & oauth_refresh_token
                 .accessTokenConverter(accessTokenConverter());
    }
    
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
    	JwtAccessTokenConverter converter = new JwtAccessTokenConverter(){
    		@Override
    		public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
    			String userName = authentication.getUserAuthentication().getName();
				final Map<String, Object> additionalInformation = new HashMap<>();
				additionalInformation.put("user_name", userName);
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				OAuth2AccessToken token = super.enhance(accessToken, authentication);
				return token;
    		}
    			
    	};
    	KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("kevin_key.jks"),"123456".toCharArray())
    						.getKeyPair("kevin_key");
    	return converter;
    }
}
