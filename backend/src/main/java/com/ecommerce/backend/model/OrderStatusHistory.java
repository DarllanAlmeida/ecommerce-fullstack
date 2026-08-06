package com.ecommerce.backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private OrderStatus oldStatus;


    private OrderStatus newStatus;



    private LocalDateTime changedAt;



    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
