package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {
	//	public static final String PaymentSrv_URL = "http://localhost:8001";
	public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称
	@Resource
	private RestTemplate restTemplate;

	@PostMapping(value = "/consumer/pay/add")
	public ResultData addOrder(@RequestBody PayDTO payDTO) {
		return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
	}

	@DeleteMapping(value = "/consumer/pay/del/{id}")
	public ResultData<Integer> deleteOrder(@PathVariable("id") Integer id) {
		return restTemplate.exchange(PaymentSrv_URL + "/pay/del/" + id, HttpMethod.DELETE, new HttpEntity<>(id), ResultData.class).getBody();
	}

	@PutMapping(value = "/consumer/pay/update")
	public ResultData<String> updateOrder(@RequestBody PayDTO payDTO) {
		//System.out.println(pay.toString());
		return restTemplate.exchange(PaymentSrv_URL + "/pay/update", HttpMethod.PUT, new HttpEntity<>(payDTO), ResultData.class).getBody();
	}

	@GetMapping(value = "/consumer/pay/get/{id}")
	public ResultData getPayInfo(@PathVariable("id") Integer id) {
		return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
	}

	@GetMapping(value = "/consumer/pay/get/info")
	private String getInfoByConsul() {
		return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
	}

	@Resource
	private DiscoveryClient discoveryClient;

	@GetMapping("/consumer/discovery")
	public String discovery() {
		List<String> services = discoveryClient.getServices();
		for (String element : services) {
			System.out.println(element);
		}

		System.out.println("===================================");

		List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
		for (ServiceInstance element : instances) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
		}

		return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
	}

}
