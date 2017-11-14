package com.hzcf.operation.configure;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hzcf.operation.mybatis.pagination.helper.PaginationInterceptor;

/**
 * Create by hanlin on 2017年11月8日
 **/
@Configuration
public class PageConfig {
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor pageHelper = new PaginationInterceptor();
	    Properties p = new Properties();
	    p.setProperty("dbms", "mysql");
	    p.setProperty("sqlRegex", ".*WithRowbounds*");
	    pageHelper.setProperties(p);
	    return pageHelper;
	}
}
