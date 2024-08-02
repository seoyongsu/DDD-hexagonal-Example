package org.example.order.domain.event;


import org.example.order.domain.entity.Order;

/**
 * 주문 생성 이벤트
 *
 * @author seoyongsu
 */
public class OrderCreateEvent extends OrderEvent{
    public OrderCreateEvent(Order order) {
        super(order);
    }
}
