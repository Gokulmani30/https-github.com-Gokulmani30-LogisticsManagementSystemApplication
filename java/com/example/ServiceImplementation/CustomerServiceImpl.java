package com.example.ServiceImplementation;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.DTO.CustomerRequest;
import com.example.DTO.CustomerResponse;
import com.example.Entity.Customer;
import com.example.Mapper.CustomerMapper;
import com.example.Repository.CustomerRepository;
import com.example.Service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

	 private final CustomerRepository customerRepository;
	    private final CustomerMapper customerMapper;

	    @Override
	    public CustomerResponse createCustomer(
	            CustomerRequest request) {

	        Customer customer =
	                customerMapper.toEntity(request);

	        Customer savedCustomer =
	                customerRepository.save(customer);

	        return customerMapper.toResponse(savedCustomer);
	    }
	    
	    @Override
	    public Page<CustomerResponse> getCustomersPage(
	            Pageable pageable) {

	        Page<Customer> customers =
	                customerRepository.findAll(pageable);

	        return customers.map(
	                customerMapper::toResponse);
	    }

	    @Cacheable(value = "customers",
	    		key = "#id")
	    @Override
	    public CustomerResponse getCustomerById(Long id) {

	        Customer customer =
	                customerRepository.findById(id)
	                .orElseThrow(() ->
	                    new RuntimeException(
	                        "Customer not found with id: " + id
	                    )
	                );

	        return customerMapper.toResponse(customer);
	    }

	    @Override
	    public List<CustomerResponse> getAllCustomers() {

	        return customerRepository.findAll()
	                .stream()
	                .map(customerMapper::toResponse)
	                .toList();
	    }

	    @CacheEvict(value = "customers",
	    		key = "#id")
	    @Override
	    public CustomerResponse updateCustomer(
	            Long id,
	            CustomerRequest request) {

	        Customer customer =
	                customerRepository.findById(id)
	                .orElseThrow(() ->
	                    new RuntimeException(
	                        "Customer not found with id: " + id
	                    )
	                );

	        customerMapper.updateEntity(
	                customer,
	                request
	        );

	        Customer updatedCustomer =
	                customerRepository.save(customer);

	        return customerMapper.toResponse(updatedCustomer);
	    }

	    @CacheEvict(value = "customers",
	    		key = "#id")
	    @Override
	    public void deleteCustomer(Long id) {

	        if (!customerRepository.existsById(id)) {

	            throw new RuntimeException(
	                "Customer not found with id: " + id
	            );
	        }

	        customerRepository.deleteById(id);
	    }
	    

}
