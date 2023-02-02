
package com.OrderService.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class OrderDemo {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	
	private int OrderId;
	private String carName;
	private String model;
	private String FCode;
	private String Indicator;
	private String Date;
	private Long SysCode;
	private Long dealerId;
	
	public OrderDemo() {
		super();
	}

	public OrderDemo(int orderId, String carName, String model, String fCode, String indicator, String date,
			Long sysCode, Long dealerId) {
		super();
		OrderId = orderId;
		this.carName = carName;
		this.model = model;
		FCode = fCode;
		Indicator = indicator;
		Date = date;
		SysCode = sysCode;
		this.dealerId = dealerId;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFCode() {
		return FCode;
	}

	public void setFCode(String fCode) {
		FCode = fCode;
	}

	public String getIndicator() {
		return Indicator;
	}

	public void setIndicator(String indicator) {
		Indicator = indicator;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Long getSysCode() {
		return SysCode;
	}

	public void setSysCode(Long sysCode) {
		SysCode = sysCode;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	@Override
	public String toString() {
		return "OrderDemo [OrderId=" + OrderId + ", carName=" + carName + ", model=" + model + ", FCode=" + FCode
				+ ", Indicator=" + Indicator + ", Date=" + Date + ", SysCode=" + SysCode + ", dealerId=" + dealerId
				+ "]";
	}
	
}