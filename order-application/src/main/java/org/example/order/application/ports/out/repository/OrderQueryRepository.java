package org.example.order.application.ports.out.repository;

import org.example.order.domain.entity.Order;
import org.example.order.domain.vo.OrderNo;

import java.util.Optional;

public interface OrderQueryRepository {
    Optional<Order> findById(OrderNo orderNo);
}
