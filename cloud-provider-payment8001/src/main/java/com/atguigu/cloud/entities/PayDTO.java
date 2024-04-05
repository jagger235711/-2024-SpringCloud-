package com.atguigu.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayDTO implements Serializable {
	private Integer id;
	/**
	 * 支付流水号
	 */
	@Column(name = "pay_no")
	private String payNo;

	/**
	 * 订单流水号
	 */
	@Column(name = "order_no")
	private String orderNo;

	/**
	 * 用户账号ID
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 交易金额
	 */
	private BigDecimal amount;
}
