package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.AddressRequestDTO;
import com.ecommerce.backend.dto.AddressResponseDTO;

import java.util.List;

public interface AddressService {

    List<AddressResponseDTO> getAll();

    AddressResponseDTO getById(Long id);

    List<AddressResponseDTO> getByCustomer(Long customerId);

    AddressResponseDTO create(AddressRequestDTO dto);

    AddressResponseDTO update(Long id, AddressRequestDTO dto);

    void delete(Long id);

}