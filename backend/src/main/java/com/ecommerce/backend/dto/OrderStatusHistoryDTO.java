package com.ecommerce.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class OrderStatusHistoryDTO {


    private String oldStatus;


    private String newStatus;


    private LocalDateTime changedAt;

}
