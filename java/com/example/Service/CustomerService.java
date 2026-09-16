package com.example.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.DTO.CustomerRequest;
import com.example.DTO.CustomerResponse;

public interface CustomerService {

	CustomerResponse createCustomer(CustomerRequest request);
	
	CustomerResponse getCustomerById(Long id);
	
	List<CustomerResponse> getAllCustomers();
	
	Page<CustomerResponse> getCustomersPage(Pageable pageable);
	
	CustomerResponse updateCustomer(Long id, CustomerRequest request);
	
	void deleteCustomer(Long id);
}
