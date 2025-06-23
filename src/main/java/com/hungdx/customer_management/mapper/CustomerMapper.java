package com.hungdx.customer_management.mapper;

import com.hungdx.customer_management.dto.CustomerRequestDto;
import com.hungdx.customer_management.dto.CustomerResponseDto;
import com.hungdx.customer_management.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {
    public abstract CustomerResponseDto mapToDto(Customer customer);
    public abstract Customer map(CustomerRequestDto dto);
}
