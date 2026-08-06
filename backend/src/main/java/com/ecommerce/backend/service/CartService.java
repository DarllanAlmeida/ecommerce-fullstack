package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CartItemRequestDTO;
import com.ecommerce.backend.dto.CartResponseDTO;

public interface CartService {

    CartResponseDTO getCart(Long customerId);

    CartResponseDTO addItem(CartItemRequestDTO dto);

    CartResponseDTO updateQuantity(Long cartItemId, Integer quantity);

    CartResponseDTO removeItem(Long cartItemId);

    void clearCart(Long customerId);

}
