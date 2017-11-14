package com.hzcf.operation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Create by hanlin on 2017年11月6日
 **/
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.hzcf.**.**.mapper")
public class OperationBootStarp {
	public static void main(String[] args) {
		SpringApplication.run(OperationBootStarp.class, args);
	}
}
