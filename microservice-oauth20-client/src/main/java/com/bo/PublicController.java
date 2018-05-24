/**
 * 
 */
package com.bo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * Copyright: Copyright (c) 2018 bobo
 * 
 * @ClassName: PublicController.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月23日 上午11:11:24 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月23日      yangbo          v1.0.0             版本创建
 */
@Controller
public class PublicController {
	
	@RequestMapping("/abc")
	@ResponseBody
    public String index() {  
        return "Hello abc";  
    }  
	
	@RequestMapping("/login")  
    public String login() {  
        return "index";  
    }  
}
