package com.atguigu.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
	@Resource
	private PayService payService;

	@PostMapping(value = "/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
	public ResultData<String> addPay(@RequestBody Pay pay) {
		System.out.println(pay.toString());
		int i = payService.add(pay);
		return ResultData.success("成功插入记录，返回值： " + i);
	}

	@DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
	public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
		//System.out.println(id);
		return ResultData.success(payService.delete(id));
	}

	@PutMapping(value = "/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
	public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
		//System.out.println(pay.toString());
		Pay pay = new Pay();
		BeanUtils.copyProperties(payDTO, pay);
		int i = payService.update(pay);
		return ResultData.success("成功修改记录，返回值： " + i);
	}

	@GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
	public ResultData<Pay> getById(@PathVariable("id") Integer id) {
		//System.out.println(id);
		if (id < 0) throw new IllegalArgumentException("id不能小于0");
		return ResultData.success(payService.getById(id));
	}

	@GetMapping(value = "/pay/get/all")
    @Operation(summary = "查询所有流水",description = "查询所有支付流水方法")
	public ResultData<List<Pay>> getAll() {
		return ResultData.success(payService.getAll());
	}

	@Value("${server.port}")
	private String port;

	@GetMapping(value = "/pay/get/info")
	public String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo) {
		return "atguiguInfo: " + atguiguInfo + "\t port: " + port;
	}
}
