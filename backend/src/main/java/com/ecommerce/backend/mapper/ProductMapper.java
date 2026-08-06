package com.ecommerce.backend.mapper;


import com.ecommerce.backend.dto.ProductRequestDTO;
import com.ecommerce.backend.dto.ProductResponseDTO;
import com.ecommerce.backend.dto.ProductVariantResponseDTO;

import com.ecommerce.backend.model.Brand;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.ProductVariant;

import java.util.stream.Collectors;



public class ProductMapper {


    private ProductMapper() {

        // Evita instanciar la clase

    }






    /**
     * Convierte ProductRequestDTO a Product
     */
    public static Product toEntity(
            ProductRequestDTO dto,
            Brand brand,
            Category category) {


        return Product.builder()

                .name(dto.getName())

                .description(dto.getDescription())

                .price(dto.getPrice())

                .stock(dto.getStock())

                .imageUrl(dto.getImageUrl())

                .brand(brand)

                .category(category)

                .active(true)

                .build();

    }









    /**
     * Convierte Product a ProductResponseDTO
     */
    public static ProductResponseDTO toDTO(Product product) {



        return ProductResponseDTO.builder()


                .id(product.getId())


                .name(product.getName())


                .description(product.getDescription())


                .price(product.getPrice())


                .stock(product.getStock())


                .imageUrl(product.getImageUrl())



                .brandId(
                        product.getBrand().getId()
                )


                .brandName(
                        product.getBrand().getName()
                )



                .categoryId(
                        product.getCategory().getId()
                )


                .categoryName(
                        product.getCategory().getName()
                )



                .variants(

                        product.getVariants() == null

                                ? null

                                :

                                product.getVariants()

                                        .stream()

                                        .map(ProductMapper::variantToDTO)

                                        .collect(Collectors.toList())

                )


                .build();

    }









    /**
     * Convierte ProductVariant a ProductVariantResponseDTO
     */
    private static ProductVariantResponseDTO variantToDTO(
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