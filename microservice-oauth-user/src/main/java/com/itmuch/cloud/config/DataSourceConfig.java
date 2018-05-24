/**
 * 
 */
package com.itmuch.cloud.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**   
 * Copyright: Copyright (c) 2017 bobo
 * 
 * @ClassName: Module.java
 * @Description: 类功能描述
 *
 * @version: v1.0.0
 * @author: yangbo
 * @date: 2018年4月22日 下午8:21:01
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年4月22日      yangbo          v1.0.0             版本创建
 */

public class DataSourceConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.h2")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
	}

    @Bean
    @ConfigurationProperties("spring.datasource.h2")
	public DataSource dataSource() {
		return h2DataSourceProperties().initializeDataSourceBuilder().build();
	}
	
}
