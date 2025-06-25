package com.hungdx.customer_management.controller;

import com.hungdx.customer_management.dto.CustomerRequestDto;
import com.hungdx.customer_management.dto.CustomerResponseDto;
import com.hungdx.customer_management.dto.validator.CreateCustomerValidatorGroup;
import com.hungdx.customer_management.service.CustomerService;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getListCustomers() {
        List<CustomerResponseDto> list = service.getAllCustomers();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Validated({Default.class}) @RequestBody CustomerRequestDto dto) {
        CustomerResponseDto response = service.createCustomer(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        service.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable String id, @Validated({Default.class, CreateCustomerValidatorGroup.class}) @RequestBody CustomerRequestDto dto) {
        CustomerResponseDto result = service.updateCustomer(dto, id);
        return ResponseEntity.ok(result);
    }

}
