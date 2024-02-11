package org.example.hexagonalexample.application.ports.out.events;

import org.example.hexagonalexample.domain.order.event.OrderCreateEvent;

/**
 * 주문 이벤트 발행
 */
public interface OrderEventPublisher {

    /**
     * 주문 생성 이벤트 발행
     */
    void publishOrderCreateEvent(OrderCreateEvent orderCreateEvent);
    
}
