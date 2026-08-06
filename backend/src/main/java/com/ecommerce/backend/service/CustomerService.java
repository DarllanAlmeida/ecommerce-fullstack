package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CustomerRequestDTO;
import com.ecommerce.backend.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> getAll();

    CustomerResponseDTO getById(Long id);

    CustomerResponseDTO getByEmail(String email);

    CustomerResponseDTO create(CustomerRequestDTO dto);

    CustomerResponseDTO update(Long id, CustomerRequestDTO dto);

    void delete(Long id);

}
