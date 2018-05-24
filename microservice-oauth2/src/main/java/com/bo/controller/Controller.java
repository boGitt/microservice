/**
 * 
 */
package com.bo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月17日 下午11:13:06
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月17日      yangbo          v1.0.0             版本创建
 */
@RestController
public class Controller {

	@GetMapping("/api/get")  
    public String get() {  
        return "Hello World!";  
    }  
  
    @PostMapping("/api/post")  
    public String post() {  
        return "POST process has finished.";  
    }  
      
    @GetMapping("/api/user")  
    public Object get(HttpServletRequest req) {  
        SecurityContextImpl sci = (SecurityContextImpl) req.getSession().getAttribute("SPRING_SECURITY_CONTEXT");  
        if (sci != null) {  
            Authentication authentication = sci.getAuthentication();  
            if (authentication != null) {  
                return authentication.getPrincipal();  
            }  
        }  
        return "none";  
    } 
    
}
