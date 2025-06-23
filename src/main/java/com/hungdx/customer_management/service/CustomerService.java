package com.hungdx.customer_management.service;

import com.hungdx.customer_management.dto.CustomerRequestDto;
import com.hungdx.customer_management.dto.CustomerResponseDto;
import com.hungdx.customer_management.exception.EmailAlreadyExistsException;
import com.hungdx.customer_management.mapper.CustomerMapper;
import com.hungdx.customer_management.model.Customer;
import com.hungdx.customer_management.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> list = repository.findAll();
        return list.stream().map(mapper::mapToDto).toList();
    }

    public Optional<CustomerResponseDto> createCustomer(CustomerRequestDto dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("createCustomer request: " + dto);
        }
        Customer customer = mapper.map(dto);
        Customer result = repository.save(customer);
        return Optional.of(mapper.mapToDto(result));
    }

}
