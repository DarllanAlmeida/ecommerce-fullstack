package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.ProductImageRequestDTO;
import com.ecommerce.backend.dto.ProductImageResponseDTO;

import java.util.List;

public interface ProductImageService {

    List<ProductImageResponseDTO> getAll();

    ProductImageResponseDTO getById(Long id);

    List<ProductImageResponseDTO> getByProduct(Long productId);

    ProductImageResponseDTO create(ProductImageRequestDTO dto);

    ProductImageResponseDTO update(Long id, ProductImageRequestDTO dto);

    void delete(Long id);

}