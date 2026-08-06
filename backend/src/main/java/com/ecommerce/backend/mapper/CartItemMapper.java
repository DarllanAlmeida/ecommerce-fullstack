package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.CartItemResponseDTO;
import com.ecommerce.backend.model.CartItem;

public class CartItemMapper {

    private CartItemMapper() {
    }

    public static CartItemResponseDTO toDTO(CartItem item) {

        return CartItemResponseDTO.builder()
                .id(item.getId())
                .productVariantId(item.getProductVariant().getId())
                .productName(item.getProductVariant().getProduct().getName())
                .size(item.getProductVariant().getSize())
                .color(item.getProductVariant().getColor())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal(item.getSubtotal())
                .build();
    }
}
