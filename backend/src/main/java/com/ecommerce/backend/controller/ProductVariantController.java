package com.ecommerce.backend.controller;


import com.ecommerce.backend.dto.ProductVariantRequestDTO;
import com.ecommerce.backend.dto.ProductVariantResponseDTO;
import com.ecommerce.backend.service.ProductVariantService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/product-variants")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductVariantController {



    private final ProductVariantService productVariantService;





    @GetMapping
    public List<ProductVariantResponseDTO> getAll(){

        return productVariantService.getAll();

    }





    @GetMapping("/{id}")
    public ProductVariantResponseDTO getById(
            @PathVariable Long id){

        return productVariantService.getById(id);

    }




}
