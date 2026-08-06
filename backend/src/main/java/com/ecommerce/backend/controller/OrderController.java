package com.ecommerce.backend.controller;


import com.ecommerce.backend.dto.OrderRequestDTO;
import com.ecommerce.backend.dto.OrderResponseDTO;
import com.ecommerce.backend.services.OrderService;


import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;


import java.util.List;





@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {



    private final OrderService orderService;









    // =========================
    // TODOS LOS PEDIDOS
    // =========================


    @GetMapping
    public List<OrderResponseDTO> getAll() {


        return orderService.getAll();


    }









    // =========================
    // PEDIDO POR ID
    // =========================


    @GetMapping("/{id}")
    public OrderResponseDTO getById(

            @PathVariable Long id

    ) {


        return orderService.getById(id);


    }









    // =========================
    // PEDIDOS POR CLIENTE
    // =========================


    @GetMapping("/customer/{customerId}")
    public List<OrderResponseDTO> getByCustomer(

            @PathVariable Long customerId

    ) {


        return orderService.getByCustomer(customerId);


    }









    // =========================
    // CHECKOUT
    // =========================


    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO checkout(

            @Valid
            @RequestBody OrderRequestDTO dto

    ) {


        return orderService.checkout(dto);


    }









    // =========================
    // ACTUALIZAR ESTADO
    // =========================


    @PutMapping("/{id}/status")
    public OrderResponseDTO updateStatus(

            @PathVariable Long id,

            @RequestParam String status

    ) {


        return orderService.updateStatus(

                id,

                status

        );


    }





}








