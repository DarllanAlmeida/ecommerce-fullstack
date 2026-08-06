package com.ecommerce.backend.services.impl;

import com.ecommerce.backend.dto.CartItemRequestDTO;
import com.ecommerce.backend.dto.CartResponseDTO;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.mapper.CartMapper;
import com.ecommerce.backend.model.Cart;
import com.ecommerce.backend.model.CartItem;
import com.ecommerce.backend.model.Customer;
import com.ecommerce.backend.model.ProductVariant;
import com.ecommerce.backend.repository.CartItemRepository;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.CustomerRepository;
import com.ecommerce.backend.repository.ProductVariantRepository;
import com.ecommerce.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductVariantRepository variantRepository;

    @Override
    public CartResponseDTO getCart(Long customerId) {

        Cart cart = getOrCreateCart(customerId);

        calculateTotals(cart);

        return CartMapper.toDTO(cart);
    }

    @Override
    public CartResponseDTO addItem(CartItemRequestDTO dto) {

        Cart cart = getOrCreateCart(dto.getCustomerId());

        ProductVariant variant = variantRepository.findById(dto.getProductVariantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Variante no encontrada"));

        Optional<CartItem> existing =
                cartItemRepository.findByCartIdAndProductVariantId(
                        cart.getId(),
                        variant.getId());

        if (existing.isPresent()) {

            CartItem item = existing.get();

            item.setQuantity(item.getQuantity() + dto.getQuantity());

            item.setSubtotal(
                    item.getUnitPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );

            cartItemRepository.save(item);

        } else {

            CartItem item = CartItem.builder()
                    .cart(cart)
                    .productVariant(variant)
                    .quantity(dto.getQuantity())
                    .unitPrice(variant.getPrice())
                    .subtotal(
                            variant.getPrice()
                                    .multiply(BigDecimal.valueOf(dto.getQuantity()))
                    )
                    .build();

            cartItemRepository.save(item);

            cart.getItems().add(item);
        }

        calculateTotals(cart);

        return CartMapper.toDTO(cart);
    }

    @Override
    public CartResponseDTO updateQuantity(Long cartItemId, Integer quantity) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item no encontrado"));

        item.setQuantity(quantity);

        item.setSubtotal(
                item.getUnitPrice()
                        .multiply(BigDecimal.valueOf(quantity))
        );

        cartItemRepository.save(item);

        Cart cart = item.getCart();

        calculateTotals(cart);

        return CartMapper.toDTO(cart);
    }

    @Override
    public CartResponseDTO removeItem(Long cartItemId) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item no encontrado"));

        Cart cart = item.getCart();

        cart.getItems().remove(item);

        cartItemRepository.delete(item);

        calculateTotals(cart);

        return CartMapper.toDTO(cart);
    }

    @Override
    public void clearCart(Long customerId) {

        Cart cart = getOrCreateCart(customerId);

        cartItemRepository.deleteAll(cart.getItems());

        cart.getItems().clear();

        calculateTotals(cart);
    }

    // -------------------------

    private Cart getOrCreateCart(Long customerId) {

        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> {

                    Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("Cliente no encontrado"));

                    Cart cart = Cart.builder()
                            .customer(customer)
                            .build();

                    return cartRepository.save(cart);

                });

    }

    private void calculateTotals(Cart cart) {

        BigDecimal subtotal = cart.getItems()
                .stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setSubtotal(subtotal);

        cart.setDiscount(BigDecimal.ZERO);

        if (subtotal.compareTo(new BigDecimal("100")) >= 0) {

            cart.setShipping(BigDecimal.ZERO);

        } else {

            cart.setShipping(new BigDecimal("5.99"));

        }

        cart.setTotal(
                cart.getSubtotal()
                        .subtract(cart.getDiscount())
                        .add(cart.getShipping())
        );

        cartRepository.save(cart);

    }

}
