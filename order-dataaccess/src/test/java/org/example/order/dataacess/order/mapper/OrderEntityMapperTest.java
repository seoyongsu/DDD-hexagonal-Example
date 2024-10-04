package org.example.order.dataacess.order.mapper;

import org.example.order.dataacess.order.entity.OrderEntity;
import org.example.order.domain.entity.Order;
import org.example.order.domain.vo.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderEntityMapperTest {

    @Test
    void toJpaEntityTest(){
        Order order = Order.builder()
                .orderNo(new OrderNo())
                .orderItems(List.of(new OrderItem("1",1)))
                .customer(new Customer("YS","용수"))
                .deliveryAddress(new Address("123", "서울", "서대문","","333",""))
                .status(OrderStatus.PAYMENT_WAITING)
                .build();

        OrderEntity orderEntity = OrderEntityMapper.toJpaEntity(order);

        assertEquals(order.getId().getNo(), orderEntity.getOrderNo());
        assertEquals(order.getId().getNo(), orderEntity.getOrderNo());
        assertEquals(order.getId().getNo(), orderEntity.getOrderNo());
        assertEquals(order.getId().getNo(), orderEntity.getOrderNo());

    }

}