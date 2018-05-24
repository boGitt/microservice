/**
 * 
 */
package com.itmuch.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itmuch.cloud.entity.User;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年2月24日 下午10:54:11
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年2月24日      yangbo          v1.0.0             版本创建
 */
@FeignClient("microservice-provider-user")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/simple/{id}", consumes = "application/json")
    User findById(@PathVariable("id") Long id);
    
}
