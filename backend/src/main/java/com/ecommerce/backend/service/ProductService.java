package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.ProductRequestDTO;
import com.ecommerce.backend.dto.ProductResponseDTO;
import com.ecommerce.backend.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAll();

    ProductResponseDTO getById(Long id);

    ProductResponseDTO save(ProductRequestDTO dto);

    ProductResponseDTO update(Long id, ProductRequestDTO dto);

    void delete(Long id);

    Product toEntity(ProductRequestDTO dto);

    ProductResponseDTO toResponse(Product product);

}