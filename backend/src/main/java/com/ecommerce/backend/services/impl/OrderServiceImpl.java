package com.ecommerce.backend.services.impl;


import com.ecommerce.backend.dto.OrderRequestDTO;
import com.ecommerce.backend.dto.OrderResponseDTO;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.mapper.OrderMapper;
import com.ecommerce.backend.model.*;
import com.ecommerce.backend.repository.*;
import com.ecommerce.backend.services.OrderService;


import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;

import java.util.List;





@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {





    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final CustomerRepository customerRepository;

    private final AddressRepository addressRepository;

    private final ProductVariantRepository variantRepository;









    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getAll() {


        return orderRepository.findAll()

                .stream()

                .map(OrderMapper::toDTO)

                .toList();


    }









    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getByCustomer(Long customerId) {


        return orderRepository.findByCustomerId(customerId)

                .stream()

                .map(OrderMapper::toDTO)

                .toList();


    }









    @Override
    @Transactional(readOnly = true)
    public OrderResponseDTO getById(Long id) {


        Order order = orderRepository.findById(id)

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "Pedido no encontrado"
                        )

                );


        return OrderMapper.toDTO(order);


    }









    @Override
    public OrderResponseDTO checkout(OrderRequestDTO dto) {


        Customer customer = customerRepository.findById(dto.getCustomerId())

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "Cliente no encontrado"
                        )

                );





        Address address = addressRepository.findById(dto.getAddressId())

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "Dirección no encontrada"
                        )

                );





        Cart cart = cartRepository.findByCustomerId(customer.getId())

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "El carrito no existe"
                        )

                );





        if(cart.getItems().isEmpty()) {

            throw new IllegalArgumentException(
                    "El carrito está vacío"
            );

        }








        // Validar stock

        for(CartItem item : cart.getItems()) {


            ProductVariant variant = item.getProductVariant();


            if(variant.getStock() < item.getQuantity()) {


                throw new IllegalArgumentException(

                        "Stock insuficiente para " +

                                variant.getProduct().getName()

                );


            }


        }









        // Crear pedido

        Order order = Order.builder()

                .customer(customer)

                .address(address)

                .subtotal(cart.getSubtotal())

                .discount(cart.getDiscount())

                .shipping(cart.getShipping())

                .total(cart.getTotal())

                .status(OrderStatus.PENDING)

                .build();




        order = orderRepository.save(order);









        // Copiar productos del carrito

        for(CartItem cartItem : cart.getItems()) {


            ProductVariant variant = cartItem.getProductVariant();





            OrderItem orderItem = OrderItem.builder()

                    .order(order)

                    .productVariant(variant)

                    .quantity(cartItem.getQuantity())

                    .unitPrice(cartItem.getUnitPrice())

                    .subtotal(cartItem.getSubtotal())

                    .build();





            order.getItems().add(orderItem);






            // descontar stock

            variant.setStock(

                    variant.getStock()
                            -
                            cartItem.getQuantity()

            );


            variantRepository.save(variant);



        }






        orderRepository.save(order);







        // Vaciar carrito

        cartItemRepository.deleteAll(cart.getItems());


        cart.getItems().clear();



        cart.setSubtotal(BigDecimal.ZERO);

        cart.setDiscount(BigDecimal.ZERO);

        cart.setShipping(BigDecimal.ZERO);

        cart.setTotal(BigDecimal.ZERO);




        cartRepository.save(cart);






        return OrderMapper.toDTO(order);


    }









    // =========================
    // ACTUALIZAR ESTADO PEDIDO
    // =========================


    @Override
    public OrderResponseDTO updateStatus(

            Long id,

            String status

    ) {



        Order order = orderRepository.findById(id)

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "Pedido no encontrado"
                        )

                );





        try {


            OrderStatus newStatus = OrderStatus.valueOf(

                    status.toUpperCase()

            );



            order.setStatus(newStatus);



        } catch (IllegalArgumentException e) {


            throw new IllegalArgumentException(

                    "Estado de pedido no válido: " + status

            );


        }






        Order saved = orderRepository.save(order);



        return OrderMapper.toDTO(saved);


    }






}
