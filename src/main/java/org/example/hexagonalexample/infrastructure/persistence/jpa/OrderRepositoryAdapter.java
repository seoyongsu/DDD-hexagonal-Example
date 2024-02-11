package org.example.hexagonalexample.infrastructure.persistence.jpa;

import org.example.hexagonalexample.application.ports.out.repository.OrderCommandRepository;
import org.example.hexagonalexample.application.ports.out.repository.OrderQueryRepository;
import org.example.hexagonalexample.domain.order.entity.Order;
import org.example.hexagonalexample.domain.order.vo.OrderNo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryAdapter implements OrderCommandRepository, OrderQueryRepository {
    private final OrderTableRepository orderTableRepository;

    public OrderRepositoryAdapter(OrderTableRepository orderTableRepository) {
        this.orderTableRepository = orderTableRepository;
    }

    @Override
    public Order save(Order order) {
        OrderTable orderTable = orderTableRepository.save(OrderTableAccessMapper.toTable(order));

        return OrderTableAccessMapper.toEntity(orderTable);
    }

    @Override
    public Optional<Order> findById(OrderNo orderNo) {
        return orderTableRepository.findById(orderNo.getNo())
                .map(OrderTableAccessMapper::toEntity);
    }
}
