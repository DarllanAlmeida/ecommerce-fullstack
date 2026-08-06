package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.CartItemRequestDTO;
import com.ecommerce.backend.dto.CartResponseDTO;
import com.ecommerce.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{customerId}")
    public CartResponseDTO getCart(@PathVariable Long customerId) {

        return cartService.getCart(customerId);

    }

    @PostMapping("/items")
    public CartResponseDTO addItem(@RequestBody CartItemRequestDTO dto) {

        return cartService.addItem(dto);

    }

    @PutMapping("/items/{cartItemId}")
    public CartResponseDTO updateQuantity(
            @PathVariable Long cartItemId,
            @RequestParam Integer quantity) {

        return cartService.updateQuantity(cartItemId, quantity);

    }

    @DeleteMapping("/items/{cartItemId}")
    public CartResponseDTO removeItem(@PathVariable Long cartItemId) {

        return cartService.removeItem(cartItemId);

    }

    @DeleteMapping("/{customerId}")
    public void clearCart(@PathVariable Long customerId) {

        cartService.clearCart(customerId);

    }

}
