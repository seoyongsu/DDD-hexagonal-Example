package org.example.hexagonalexample.application.ports.out.repository;

import org.example.hexagonalexample.domain.order.entity.Order;

public interface OrderCommandRepository {
    Order save(Order order);
}
