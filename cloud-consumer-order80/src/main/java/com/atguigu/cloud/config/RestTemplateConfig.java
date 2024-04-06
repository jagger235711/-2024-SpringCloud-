package com.atguigu.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	@Bean
	@LoadBalanced//不加会报 运行时异常:I/O error on GET request for "http://cloud-payment-service/pay/get/1": cloud-payment-service 因为consul天生支持负载均衡 所以远程调用要加注解支持负载均衡
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}