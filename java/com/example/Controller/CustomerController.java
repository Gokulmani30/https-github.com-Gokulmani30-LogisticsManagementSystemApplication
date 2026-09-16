package com.example.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ApiResponse;
import com.example.DTO.CustomerRequest;
import com.example.DTO.CustomerResponse;
import com.example.Service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;


    // ==========================================
    // CREATE CUSTOMER
    // ==========================================

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>>
            createCustomer(
                    @Valid
                    @RequestBody CustomerRequest request) {

        CustomerResponse response =
                customerService.createCustomer(request);

        ApiResponse<CustomerResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "Customer Created Successfully...",
                        response,
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }


    // ==========================================
    // GET CUSTOMER BY ID
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse>
            getCustomerById(
                    @PathVariable Long id) {

        return ResponseEntity.ok(
                customerService.getCustomerById(id)
        );
    }


    // ==========================================
    // PAGINATION + SORTING
    // ==========================================

    @GetMapping("/page")
    public ResponseEntity<Page<CustomerResponse>>
            getCustomersPage(

                    @RequestParam(
                            defaultValue = "0")
                    int page,

                    @RequestParam(
                            defaultValue = "5")
                    int size,

                    @RequestParam(
                            defaultValue = "customerId")
                    String sortBy,

                    @RequestParam(
                            defaultValue = "asc")
                    String direction) {

        Sort.Direction sortDirection;

        if (direction.equalsIgnoreCase("desc")) {

            sortDirection =
                    Sort.Direction.DESC;

        } else {

            sortDirection =
                    Sort.Direction.ASC;
        }

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(
                                sortDirection,
                                sortBy
                        )
                );

        Page<CustomerResponse> response =
                customerService
                        .getCustomersPage(pageable);

        return ResponseEntity.ok(response);
    }


    // ==========================================
    // GET ALL CUSTOMERS
    // ==========================================

    @GetMapping
    public ResponseEntity<List<CustomerResponse>>
            getAllCustomers() {

        List<CustomerResponse> customers =
                customerService.getAllCustomers();

        return ResponseEntity.ok(customers);
    }


    // ==========================================
    // UPDATE CUSTOMER
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse>
            updateCustomer(

                    @PathVariable Long id,

                    @Valid
                    @RequestBody
                    CustomerRequest request) {

        return ResponseEntity.ok(
                customerService.updateCustomer(
                        id,
                        request
                )
        );
    }


    // ==========================================
    // DELETE CUSTOMER
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
            deleteCustomer(
                    @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.ok(
                "Customer deleted successfully"
        );
    }
}

