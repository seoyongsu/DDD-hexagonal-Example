package org.example.order.application.ports.out.repository;

import org.example.order.domain.entity.Order;

public interface OrderCommandRepository {
    Order save(Order order);
}
