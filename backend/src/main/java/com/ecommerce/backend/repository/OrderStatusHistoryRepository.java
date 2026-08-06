package com.ecommerce.backend.repository;


import com.ecommerce.backend.model.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderStatusHistoryRepository
        extends JpaRepository<OrderStatusHistory, Long> {


    List<OrderStatusHistory> findByOrderId(Long orderId);

}
