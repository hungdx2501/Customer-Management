package com.hungdx.customer_management.controller;

import com.hungdx.customer_management.dto.CustomerRequestDto;
import com.hungdx.customer_management.dto.CustomerResponseDto;
import com.hungdx.customer_management.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<CustomerResponseDto> createCustomer(@Validated @RequestBody CustomerRequestDto dto) {
        Optional<CustomerResponseDto> response = service.createCustomer(dto);
        if (response.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response.get());
    }
}
