/**
 * 
 */
package com.bo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bo.oauth20.client.MyApi;
import com.bo.util.RandomCharUtil;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月22日 下午3:45:12
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月22日      yangbo          v1.0.0             版本创建
 */
@Controller
public class IndexController {

	private static final String PROFILE_URL = "http://localhost:8082/simple/1";

	private static final String SESSION_KEY_ACCESS_TOKEN = "MY_ACCESS_TOKEN";
	
	private static final String CLIENT_ID = "oauth_client_ren";
	private static final String CLIENT_SECRET = "oauth_client_secret_ren";
	private static final String CALLBACK_URL = "http://localhost:8080/callback";
	private static final String SCOPE = "read";
	
    // 为防止CSRF跨站攻击，每次请求STATE的值应该不同，可以放入Session！
    // 由于都是localhost测试，所以session无法保持，用一个固定值。
	private String STATE = RandomCharUtil.getRandomALLChar(8);

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public void signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.debug("signin");
	    logger.info("session id:{}", request.getSession().getId());
	    
		OAuth20Service service = new ServiceBuilder(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.scope(SCOPE)
				.state(STATE)
				.callback(CALLBACK_URL)
				.build(MyApi.instance());

		String authorizationUrl = service.getAuthorizationUrl();
		logger.info("redirectURL:{}", authorizationUrl);

		response.sendRedirect(authorizationUrl);
	}
	

	@GetMapping("/callback")
	public String callback(@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		logger.debug("callback");
		logger.info("code:{} state:{}", code, state);
	    logger.info("session id:{}", request.getSession().getId());
		
		if (STATE.equals(state)) {
			logger.info("State OK!");
		} else {
			logger.error("State NG!");
		}

		OAuth20Service service = new ServiceBuilder(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.scope(SCOPE)
				.state(STATE)
				.callback(CALLBACK_URL)
				.build(MyApi.instance());

		OAuth2AccessToken accessToken = service.getAccessToken(code);
		request.getSession().setAttribute(SESSION_KEY_ACCESS_TOKEN, accessToken);

		getProfile(model, service, accessToken);

		return "profile";
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		logger.debug("profile");

		OAuth2AccessToken accessToken = (OAuth2AccessToken) request.getSession().getAttribute(SESSION_KEY_ACCESS_TOKEN);

		OAuth20Service service = new ServiceBuilder(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.scope(SCOPE)
				.state(STATE)
				.callback(CALLBACK_URL)
				.build(MyApi.instance());

		getProfile(model, service, accessToken);

		return "profile";
	}


	private void getProfile(Model model, final OAuth20Service service, OAuth2AccessToken accessToken) throws Exception {
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, PROFILE_URL);
		service.signRequest(accessToken, oauthRequest);
		
		Response resourceResponse = service.execute(oauthRequest);
		
		logger.info("code:{}", resourceResponse.getCode());
		logger.info("message:{}", resourceResponse.getMessage());
		logger.info("body:{}", resourceResponse.getBody());

		JSONObject obj = new JSONObject(resourceResponse.getBody());

		logger.info("json:{}", obj.toString());

		int id = obj.getInt("id");
		String name = obj.getString("name");
		int age = obj.getInt("age");

		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
	}
	
}
