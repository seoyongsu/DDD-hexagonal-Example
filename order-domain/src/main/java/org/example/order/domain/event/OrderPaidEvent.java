package org.example.order.domain.event;


import org.example.order.domain.entity.Order;

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
