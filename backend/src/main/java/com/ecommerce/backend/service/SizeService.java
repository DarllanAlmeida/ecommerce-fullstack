package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.SizeRequestDTO;
import com.ecommerce.backend.dto.SizeResponseDTO;

import java.util.List;

public interface SizeService {

    List<SizeResponseDTO> getAll();

    SizeResponseDTO getById(Long id);

    SizeResponseDTO create(SizeRequestDTO dto);

    SizeResponseDTO update(Long id, SizeRequestDTO dto);

    void delete(Long id);

}