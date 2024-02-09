package org.example.hexagonalexample.domain.order.event;


import org.example.hexagonalexample.domain.order.entity.Order;

/**
 * 주문 결제 이벤트
 *
 * @author seoyongsu
 */
public class OrderPaidEvent extends OrderEvent{
    public OrderPaidEvent(Order order) {
        super(order);
    }
}
