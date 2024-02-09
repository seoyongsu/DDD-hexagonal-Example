package org.example.hexagonalexample.domain.order.event;


import org.example.hexagonalexample.domain.order.entity.Order;

/**
 * 주문 취소 이벤트
 */
public class OrderCanceledEvent extends OrderEvent{
    public OrderCanceledEvent(Order order) {
        super(order);
    }
}
