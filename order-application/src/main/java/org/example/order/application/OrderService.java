package org.example.order.application;


import org.example.order.application.ports.in.usecase.CreateOrderUseCase;
import org.example.order.application.ports.in.usecase.TrackOrderUseCase;
import org.example.order.application.ports.in.usecase.dto.OrderCommand;
import org.example.order.application.ports.in.usecase.dto.OrderResponse;
import org.example.order.application.ports.out.events.OrderEventPublisher;
import org.example.order.application.ports.out.repository.OrderCommandRepository;
import org.example.order.application.ports.out.repository.OrderQueryRepository;
import org.example.order.domain.OrderDomainService;
import org.example.order.domain.entity.Order;
import org.example.order.domain.event.OrderCreateEvent;
import org.example.order.domain.exception.OrderNotFoundException;
import org.example.order.domain.vo.OrderNo;
import org.example.order.domain.vo.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService implements CreateOrderUseCase, TrackOrderUseCase {
    private final OrderDomainService orderDomainService = new OrderDomainService() {};
    private final OrderQueryRepository orderQueryRepository;
    private final OrderCommandRepository orderCommandRepository;
    private final OrderEventPublisher paymentEventPublisher;

    public OrderService(OrderQueryRepository orderQueryRepository, OrderCommandRepository orderCommandRepository, OrderEventPublisher paymentEventPublisher) {
        this.orderQueryRepository = orderQueryRepository;
        this.orderCommandRepository = orderCommandRepository;
        this.paymentEventPublisher = paymentEventPublisher;
    }

    @Override
    @Transactional
    public OrderResponse createOrder(OrderCommand cmd) {
        Order order = orderCommandRepository.save(Order.builder()
                .orderNo(new OrderNo())
                .orderItems(cmd.orderItems())
                .customer(cmd.customer())
                .deliveryAddress(cmd.deliveryAddress())
                .status(OrderStatus.PAYMENT_WAITING)
                .build()
        );

        OrderCreateEvent orderCreateEvent = orderDomainService.validateAndCreateOrder(order);
        paymentEventPublisher.publishOrderCreateEvent(orderCreateEvent);
        return new OrderResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse trackOrder(OrderNo orderNo) {
        Order order = orderQueryRepository.findById(orderNo)
                .orElseThrow(OrderNotFoundException::new);
        return new OrderResponse(order);
    }
}
