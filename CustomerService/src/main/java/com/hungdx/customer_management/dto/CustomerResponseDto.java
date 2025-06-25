package com.hungdx.customer_management.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CustomerResponseDto {
    private String id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;
}
