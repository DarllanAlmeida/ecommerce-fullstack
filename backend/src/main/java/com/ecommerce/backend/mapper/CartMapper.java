package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.CartResponseDTO;
import com.ecommerce.backend.model.Cart;

public class CartMapper {

    private CartMapper() {
    }

    public static CartResponseDTO toDTO(Cart cart) {

        return CartResponseDTO.builder()
                .id(cart.getId())
                .customerId(cart.getCustomer().getId())
                .customerName(
                        cart.getCustomer().getFirstName() + " " +
                                cart.getCustomer().getLastName()
                )
                .subtotal(cart.getSubtotal())
                .discount(cart.getDiscount())
                .shipping(cart.getShipping())
                .total(cart.getTotal())
                .items(
                        cart.getItems()
                                .stream()
                                .map(CartItemMapper::toDTO)
                                .toList()
                )
                .build();
    }

}
