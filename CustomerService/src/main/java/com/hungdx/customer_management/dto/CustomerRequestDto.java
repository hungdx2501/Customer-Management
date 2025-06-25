package com.hungdx.customer_management.dto;

import com.hungdx.customer_management.dto.validator.CreateCustomerValidatorGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequestDto {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters", groups = {CreateCustomerValidatorGroup.class})
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Address is required", groups =  {CreateCustomerValidatorGroup.class})
    private String address;

    @NotBlank(message = "Date of birth is required", groups =  {CreateCustomerValidatorGroup.class})
    private String dateOfBirth;
}
