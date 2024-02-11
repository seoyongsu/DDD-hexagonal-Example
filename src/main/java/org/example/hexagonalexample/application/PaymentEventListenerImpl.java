package org.example.hexagonalexample.application;

import org.example.hexagonalexample.application.ports.in.events.PaymentEventListener;
import org.example.hexagonalexample.application.ports.in.events.dto.OrderPaymentCommand;
import org.example.hexagonalexample.application.ports.out.repository.OrderCommandRepository;
import org.example.hexagonalexample.application.ports.out.repository.OrderQueryRepository;
import org.example.hexagonalexample.domain.order.OrderDomainService;
import org.example.hexagonalexample.domain.order.entity.Order;
import org.example.hexagonalexample.domain.order.event.OrderPaidEvent;
import org.example.hexagonalexample.domain.order.exception.OrderNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class PaymentEventListenerImpl implements PaymentEventListener {

    private final OrderDomainService orderDomainService = new OrderDomainService() {};

    private final OrderCommandRepository orderCommandRepository;
    private final OrderQueryRepository orderQueryRepository;

    PaymentEventListenerImpl(OrderCommandRepository orderCommandRepository, OrderQueryRepository orderQueryRepository) {
        this.orderCommandRepository = orderCommandRepository;
        this.orderQueryRepository = orderQueryRepository;
    }

    @Override
    @Transactional
    public void paymentCompleted(OrderPaymentCommand cmd) {
        if(!OrderPaymentCommand.Status.COMPLETED.equals(cmd.paymentStatus())){
            throw new RuntimeException();
        }
        Order order = orderQueryRepository.findById(cmd.orderNo())
                .orElseThrow(OrderNotFoundException::new);
        OrderPaidEvent orderPaidEvent = orderDomainService.payOrder(order);
        orderCommandRepository.save(orderPaidEvent.getOrder());
    }

    @Override
    @Transactional
    public void paymentCanceled(OrderPaymentCommand cmd) {
        if(!OrderPaymentCommand.Status.COMPLETED.equals(cmd.paymentStatus())){
            throw new RuntimeException();
        }
        Order order = orderQueryRepository.findById(cmd.orderNo())
                .orElseThrow(OrderNotFoundException::new);
        orderDomainService.cancelOrder(order);
    }
}
