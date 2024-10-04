package org.example.order.application.ports.out.events;

import org.example.order.domain.event.OrderCanceledEvent;
import org.example.order.domain.event.OrderCreateEvent;
import org.example.order.domain.event.OrderPaidEvent;

/**
 * 주문 이벤트 발행
 */
public interface OrderEventPublisher {

    /**
     * 주문 생성 이벤트 발행
     */
    void publishOrderCreateEvent(OrderCreateEvent orderCreateEvent);

    void publishOrderPaidEvent(OrderPaidEvent orderPaidEvent);

    void publishOrderCanceledEvent(OrderCanceledEvent orderCanceledEvent);
    
}
