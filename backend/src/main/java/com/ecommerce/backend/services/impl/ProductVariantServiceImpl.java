package com.ecommerce.backend.services.impl;


import com.ecommerce.backend.dto.ProductVariantResponseDTO;
import com.ecommerce.backend.mapper.ProductVariantMapper;
import com.ecommerce.backend.model.ProductVariant;
import com.ecommerce.backend.repository.ProductVariantRepository;
import com.ecommerce.backend.service.ProductVariantService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional
public class ProductVariantServiceImpl implements ProductVariantService {



    private final ProductVariantRepository productVariantRepository;





    @Override
    @Transactional(readOnly = true)
    public List<ProductVariantResponseDTO> getAll() {


        return productVariantRepository
                .findAll()
                .stream()
                .map(ProductVariantMapper::toDTO)
                .toList();


    }







    @Override
    @Transactional(readOnly = true)
    public ProductVariantResponseDTO getById(Long id) {


        ProductVariant variant =
                productVariantRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Variante no encontrada"
                                )
                        );



        return ProductVariantMapper.toDTO(variant);


    }



}
