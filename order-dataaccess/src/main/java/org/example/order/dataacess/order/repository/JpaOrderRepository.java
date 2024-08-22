package org.example.order.dataacess.order.repository;

import org.example.order.dataacess.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, String> {
}
