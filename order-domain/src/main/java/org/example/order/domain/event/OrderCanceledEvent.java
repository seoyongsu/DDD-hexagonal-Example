package org.example.order.domain.event;


import org.example.order.domain.entity.Order;

/**
 * 주문 취소 이벤트
 */
public class OrderCanceledEvent extends OrderEvent{
    public OrderCanceledEvent(Order order) {
        super(order);
    }
}
