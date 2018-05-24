/**
 * 
 */
package com.bo.oauth20.client;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.oauth.OAuth20Service;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月22日 下午3:50:59
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月22日      yangbo          v1.0.0             版本创建
 */
public class MyApi extends DefaultApi20 {
	
	protected MyApi() {
    }

    private static class InstanceHolder {
        private static final MyApi INSTANCE = new MyApi();
    }

    public static MyApi instance() {
        return InstanceHolder.INSTANCE;
    }
    
	/* (non-Javadoc)
	 * @see com.github.scribejava.core.builder.api.DefaultApi20#getAccessTokenEndpoint()
	 */
	@Override
	public String getAccessTokenEndpoint() {
		// TODO Auto-generated method stub
		return "http://localhost:8081/oauth/token";
	}

	/* (non-Javadoc)
	 * @see com.github.scribejava.core.builder.api.DefaultApi20#getAuthorizationBaseUrl()
	 */
	@Override
	protected String getAuthorizationBaseUrl() {
		// TODO Auto-generated method stub
		return "http://localhost:8081/oauth/authorize";
	}

	@Override
	public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
		return OAuth2AccessTokenJsonExtractor.instance();
	}

	@Override
	public OAuth20Service createService(OAuthConfig config) {
		return new MyOAuth20Service(this, config);
	}
}
