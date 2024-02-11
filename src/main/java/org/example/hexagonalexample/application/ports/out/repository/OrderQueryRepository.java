package org.example.hexagonalexample.application.ports.out.repository;

import org.example.hexagonalexample.domain.order.entity.Order;
import org.example.hexagonalexample.domain.order.vo.OrderNo;

import java.util.Optional;

public interface OrderQueryRepository {
    Optional<Order> findById(OrderNo orderNo);
}
