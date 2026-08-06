package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.CustomerRequestDTO;
import com.ecommerce.backend.dto.CustomerResponseDTO;
import com.ecommerce.backend.model.Customer;
import com.ecommerce.backend.model.Role;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer toEntity(CustomerRequestDTO dto, Role role) {

        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .role(role)
                .active(true)
                .build();
    }

    public static CustomerResponseDTO toDTO(Customer customer) {

        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .roleId(customer.getRole().getId())
                .roleName(customer.getRole().getName())
                .active(customer.getActive())
                .build();
    }

}
