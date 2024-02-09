package org.example.hexagonalexample.domain.order.event;


import org.example.hexagonalexample.domain.order.entity.Order;

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
