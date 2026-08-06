package com.ecommerce.backend.service;


import com.ecommerce.backend.dto.ProductVariantResponseDTO;


import java.util.List;


public interface ProductVariantService {


    List<ProductVariantResponseDTO> getAll();


    ProductVariantResponseDTO getById(Long id);


}
