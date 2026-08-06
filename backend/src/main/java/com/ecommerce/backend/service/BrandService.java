package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.BrandRequestDTO;
import com.ecommerce.backend.dto.BrandResponseDTO;
import com.ecommerce.backend.model.Brand;

import java.util.List;

public interface BrandService {

    List<BrandResponseDTO> findAll();

    BrandResponseDTO findById(Long id);

    BrandResponseDTO create(BrandRequestDTO dto);

    BrandResponseDTO update(Long id, BrandRequestDTO dto);

    void delete(Long id);

    Brand toEntity(BrandRequestDTO dto);

    BrandResponseDTO toResponse(Brand brand);
}

