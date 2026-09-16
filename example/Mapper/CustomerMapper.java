package com.example.Mapper;

import org.springframework.stereotype.Component;

import com.example.DTO.CustomerRequest;
import com.example.DTO.CustomerResponse;
import com.example.Entity.Customer;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {

        Customer customer = new Customer();

        customer.setCustomerName(request.getCustomerName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());

        return customer;
    }

    public CustomerResponse toResponse(Customer customer) {

        CustomerResponse response = new CustomerResponse();

        response.setCustomerId(customer.getCustomerId());
        response.setCustomerName(customer.getCustomerName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setAddress(customer.getAddress());
        response.setCreatedAt(customer.getCreatedAt());

        return response;
    }

    public void updateEntity(
            Customer customer,
            CustomerRequest request) {

        customer.setCustomerName(request.getCustomerName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
    }
}