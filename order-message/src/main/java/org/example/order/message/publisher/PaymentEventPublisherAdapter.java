package org.example.order.message.publisher;

import lombok.extern.slf4j.Slf4j;
import org.example.order.application.ports.out.events.OrderEventPublisher;
import org.example.order.domain.event.OrderCanceledEvent;
import org.example.order.domain.event.OrderCreateEvent;
import org.example.order.domain.event.OrderPaidEvent;
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

    @Override
    public void publishOrderPaidEvent(OrderPaidEvent orderPaidEvent) {

    }

    @Override
    public void publishOrderCanceledEvent(OrderCanceledEvent orderCanceledEvent) {

    }
}