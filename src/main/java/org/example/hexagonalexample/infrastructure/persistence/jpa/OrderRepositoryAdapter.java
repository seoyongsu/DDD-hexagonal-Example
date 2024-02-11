package org.example.hexagonalexample.infrastructure.persistence.jpa;

import org.example.hexagonalexample.application.ports.out.repository.OrderCommandRepository;
import org.example.hexagonalexample.application.ports.out.repository.OrderQueryRepository;
import org.example.hexagonalexample.domain.order.entity.Order;
import org.example.hexagonalexample.domain.order.vo.OrderNo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryAdapter implements OrderCommandRepository, OrderQueryRepository {

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Optional<Order> findById(OrderNo orderNo) {
        return Optional.empty();
    }
}
