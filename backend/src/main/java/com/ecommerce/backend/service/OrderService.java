package com.ecommerce.backend.services;


import com.ecommerce.backend.dto.OrderRequestDTO;
import com.ecommerce.backend.dto.OrderResponseDTO;


import java.util.List;




public interface OrderService {





    // =========================
    // TODOS LOS PEDIDOS
    // =========================

    List<OrderResponseDTO> getAll();








    // =========================
    // PEDIDOS POR CLIENTE
    // =========================

    List<OrderResponseDTO> getByCustomer(

            Long customerId

    );








    // =========================
    // PEDIDO POR ID
    // =========================

    OrderResponseDTO getById(

            Long id

    );








    // =========================
    // CHECKOUT
    // =========================

    OrderResponseDTO checkout(

            OrderRequestDTO dto

    );








    // =========================
    // CAMBIAR ESTADO PEDIDO
    // =========================

    OrderResponseDTO updateStatus(

            Long id,

            String status

    );





}
