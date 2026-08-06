package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.ProductImageRequestDTO;
import com.ecommerce.backend.dto.ProductImageResponseDTO;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.ProductImage;

public class ProductImageMapper {

    private ProductImageMapper() {
    }

    public static ProductImage toEntity(ProductImageRequestDTO dto, Product product) {

        return ProductImage.builder()
                .imageUrl(dto.getImageUrl())
                .altText(dto.getAltText())
                .displayOrder(dto.getDisplayOrder())
                .product(product)
                .active(true)
                .build();
    }

    public static ProductImageResponseDTO toDTO(ProductImage image) {

        return ProductImageResponseDTO.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .altText(image.getAltText())
                .displayOrder(image.getDisplayOrder())
                .active(image.getActive())
                .productId(image.getProduct().getId())
                .productName(image.getProduct().getName())
                .build();
    }

}
