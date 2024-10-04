package org.example.order.application;

import org.example.order.application.ports.in.events.PaymentEventListener;
import org.example.order.application.ports.in.events.dto.OrderPaymentCommand;
import org.example.order.application.ports.out.events.OrderEventPublisher;
import org.example.order.application.ports.out.repository.OrderCommandRepository;
import org.example.order.application.ports.out.repository.OrderQueryRepository;
import org.example.order.domain.OrderDomainService;
import org.example.order.domain.entity.Order;
import org.example.order.domain.exception.OrderDomainException;
import org.example.order.domain.exception.OrderNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class PaymentEventListenerImpl implements PaymentEventListener {

    private final OrderDomainService orderDomainService = new OrderDomainService() {};
    private final OrderCommandRepository orderCommandRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final OrderEventPublisher orderEventPublisher;

    PaymentEventListenerImpl(OrderCommandRepository orderCommandRepository, OrderQueryRepository orderQueryRepository, OrderEventPublisher orderEventPublisher) {
        this.orderCommandRepository = orderCommandRepository;
        this.orderQueryRepository = orderQueryRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Override
    @Transactional
    public void paymentCompleted(OrderPaymentCommand cmd) {
        if(!OrderPaymentCommand.Status.COMPLETED.equals(cmd.paymentStatus())){
            throw new OrderDomainException("Payment Completed Fail");
        }
        Order order = orderQueryRepository.findById(cmd.orderNo())
                .orElseThrow(OrderNotFoundException::new);

        orderEventPublisher.publishOrderPaidEvent( orderDomainService.payOrder(order) );
        orderCommandRepository.save(order);
    }

    @Override
    @Transactional
    public void paymentCanceled(OrderPaymentCommand cmd) {
        if(!OrderPaymentCommand.Status.CANCELLED.equals(cmd.paymentStatus())){
            throw new OrderDomainException("Payment Canceled Fail");
        }
        Order order = orderQueryRepository.findById(cmd.orderNo())
                .orElseThrow(OrderNotFoundException::new);

        orderEventPublisher.publishOrderCanceledEvent( orderDomainService.cancelOrder(order) );
        orderCommandRepository.save(order);
    }
}
