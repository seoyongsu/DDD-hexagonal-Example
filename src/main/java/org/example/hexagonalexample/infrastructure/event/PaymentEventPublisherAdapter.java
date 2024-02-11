package org.example.hexagonalexample.infrastructure.event;

import lombok.extern.slf4j.Slf4j;
import org.example.hexagonalexample.application.ports.out.events.OrderEventPublisher;
import org.example.hexagonalexample.domain.order.event.OrderCreateEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentEventPublisherAdapter implements OrderEventPublisher {

    @Override
    public void publishOrderCreateEvent(OrderCreateEvent orderCreateEvent) {
        //TODO ...
        // 1. payment service to push
        // 2. order service publish
        log.info("Order Create Payload Publish!!!  {}", orderCreateEvent.getOrder().getId().getNo());
    }
}
