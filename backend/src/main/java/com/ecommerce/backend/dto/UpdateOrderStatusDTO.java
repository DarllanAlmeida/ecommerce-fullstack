package com.ecommerce.backend.dto;

import com.ecommerce.backend.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateOrderStatusDTO {


    private OrderStatus status;

}
