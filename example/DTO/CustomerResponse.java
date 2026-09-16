package com.example.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

	private Long customerId;
	
	private String customerName;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	private LocalDateTime createdAt;
}
