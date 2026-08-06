package com.ecommerce.backend.mapper;


import com.ecommerce.backend.dto.ProductVariantRequestDTO;
import com.ecommerce.backend.dto.ProductVariantResponseDTO;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.ProductVariant;



public class ProductVariantMapper {


    private ProductVariantMapper() {

    }





    public static ProductVariant toEntity(
            ProductVariantRequestDTO dto,
            Product product) {


        return ProductVariant.builder()

                .sku(dto.getSku())

                .size(dto.getSize())

                .color(dto.getColor())

                .price(dto.getPrice())

                .stock(dto.getStock())

                .active(true)

                .product(product)

                .build();

    }







    public static ProductVariantResponseDTO toDTO(
            ProductVariant variant) {


        return ProductVariantResponseDTO.builder()


                .id(
                        variant.getId()
                )


                .sku(
                        variant.getSku()
                )


                .size(
                        variant.getSize()
                )


                .color(
                        variant.getColor()
                )


                .price(
                        variant.getPrice()
                )


                .stock(
                        variant.getStock()
                )


                .build();

    }


}