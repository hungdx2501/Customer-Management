package com.hungdx.customer_management.service;

import com.hungdx.customer_management.dto.CustomerRequestDto;
import com.hungdx.customer_management.dto.CustomerResponseDto;
import com.hungdx.customer_management.exception.EmailAlreadyExistsException;
import com.hungdx.customer_management.exception.UserNotFoundException;
import com.hungdx.customer_management.mapper.CustomerMapper;
import com.hungdx.customer_management.model.Customer;
import com.hungdx.customer_management.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> list = repository.findAll();
        return list.stream().map(mapper::mapToDto).toList();
    }

    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("createCustomer request: " + dto);
        }
        Customer customer = mapper.map(dto);
        customer.setRegisteredDate(LocalDate.now());
        Customer result = repository.save(customer);
        return mapper.mapToDto(result);
    }

    public void deleteCustomer(String id) {
        Optional<Customer> customer = repository.findById(UUID.fromString(id));
        if (customer.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        repository.delete(customer.get());
    }

    public CustomerResponseDto updateCustomer(CustomerRequestDto dto, String id) {
        Customer customer = repository.findByEmail(dto.getEmail()).orElseThrow(() -> new UserNotFoundException("updateCustomer request: " + dto));

        if (repository.existsByEmailAndIdNot(customer.getEmail(), UUID.fromString(id))) {
            throw new UserNotFoundException("updateCustomer 2 request: " + dto);
        }

        if (dto.getName() != null) {
            customer.setName(dto.getName());
        }

        if (dto.getAddress() != null) {
            customer.setAddress(dto.getAddress());
        }

        if (dto.getDateOfBirth() != null) {
            customer.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        }

        Customer result = repository.save(customer);
        return mapper.mapToDto(result);
    }
}
