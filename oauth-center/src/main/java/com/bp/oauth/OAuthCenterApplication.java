package com.bp.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 认证中心
 * 
 * @author 钟欣凯
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class OAuthCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthCenterApplication.class, args);
	}

}