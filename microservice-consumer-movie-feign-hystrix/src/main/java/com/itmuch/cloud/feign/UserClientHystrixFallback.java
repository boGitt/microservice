/**
 * 
 */
package com.itmuch.cloud.feign;

import org.springframework.stereotype.Component;

import com.itmuch.cloud.entity.User;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年2月27日 下午9:48:03
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年2月27日      yangbo          v1.0.0             版本创建
 */
@Component
public class UserClientHystrixFallback implements UserClient {

	/* (non-Javadoc)
	 * @see com.itmuch.cloud.feign.UserClient#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(0L);
		return user;
	}

}
