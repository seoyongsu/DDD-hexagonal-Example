package org.example.order.dataacess.order.adapter;

import org.example.order.application.ports.out.repository.OrderCommandRepository;
import org.example.order.application.ports.out.repository.OrderQueryRepository;
import org.example.order.dataacess.order.entity.OrderEntity;
import org.example.order.dataacess.order.mapper.OrderEntityMapper;
import org.example.order.dataacess.order.repository.JpaOrderRepository;
import org.example.order.domain.entity.Order;
import org.example.order.domain.vo.OrderNo;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public class OrderRepositoryAdapter implements OrderCommandRepository, OrderQueryRepository {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        jpaOrderRepository.save( OrderEntityMapper.toJpaEntity(order));
        return order;
    }

    @Override
    public Optional<Order> findById(OrderNo orderNo) {

        return jpaOrderRepository.findById(orderNo.getNo())
                .map(OrderEntityMapper::toEntity);
    }


}
