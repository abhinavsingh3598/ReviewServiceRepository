package com.eatza.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CustomerDto {

	long customerId;
	String customerName;
	int age;
	private String status;

	public CustomerDto(long customerId, String customerName, int age, String status) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.age = age;
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", customerName=" + customerName + ", age=" + age + ", status="
				+ status + "]";
	}

	
}
