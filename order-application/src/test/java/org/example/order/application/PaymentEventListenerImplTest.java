package org.example.order.application;

import org.example.order.application.ports.in.events.dto.OrderPaymentCommand;
import org.example.order.application.ports.out.events.OrderEventPublisher;
import org.example.order.application.ports.out.repository.OrderCommandRepository;
import org.example.order.application.ports.out.repository.OrderQueryRepository;
import org.example.order.domain.OrderDomainService;
import org.example.order.domain.entity.Order;
import org.example.order.domain.event.OrderCanceledEvent;
import org.example.order.domain.event.OrderPaidEvent;
import org.example.order.domain.exception.OrderDomainException;
import org.example.order.domain.exception.OrderNotFoundException;
import org.example.order.domain.vo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentEventListenerImplTest {

    @Mock
    private OrderDomainService orderDomainService;

    @Mock
    private OrderCommandRepository orderCommandRepository;

    @Mock
    private OrderQueryRepository orderQueryRepository;

    @Mock
    private OrderEventPublisher orderEventPublisher;

    @InjectMocks
    private PaymentEventListenerImpl paymentEventListener;




//    @Test
    void paymentCompleted_Success() {
        OrderPaymentCommand cmd = new OrderPaymentCommand(new OrderNo("1234"), "paymentID-1234", OrderPaymentCommand.Status.COMPLETED);

        Order order = Order.builder()
                .orderNo(cmd.orderNo())
                .orderItems(List.of(new OrderItem("1", 10)))
                .customer(new Customer("YS","용수"))
                .deliveryAddress(new Address("123", "서울", "서대문","","333",""))
                .status(OrderStatus.PAYMENT_WAITING)
                .build();

        Mockito.when(orderQueryRepository.findById(cmd.orderNo())).thenReturn(Optional.of(order));

        paymentEventListener.paymentCompleted(cmd);
        Mockito.verify(orderCommandRepository, Mockito.times(1)).save(order);
        Mockito.verify(orderEventPublisher, Mockito.times(1)).publishOrderPaidEvent(Mockito.any(OrderPaidEvent.class));
    }

    @Test
    void paymentCompleted_Fail_OrderNotFound(){
        OrderPaymentCommand cmd = new OrderPaymentCommand(new OrderNo("1234"), "paymentID-1234", OrderPaymentCommand.Status.COMPLETED);
        Mockito.when(orderQueryRepository.findById(cmd.orderNo())).thenReturn(Optional.empty());
        // When & Then
        assertThrows(OrderNotFoundException.class, () -> {
            paymentEventListener.paymentCompleted(cmd);
        });
    }

    @Test
    void paymentCompleted_Fail_PaymentStatus() {
        OrderPaymentCommand cmd = new OrderPaymentCommand(new OrderNo("1234"), "paymentID-1234", OrderPaymentCommand.Status.CANCELLED);
        assertThrows(OrderDomainException.class, () -> {
            paymentEventListener.paymentCompleted(cmd);
        });
    }

    /**
     * 이미 결제된 주문 Test
     */
    @Test
    void paymentCompleted_Fail_Paid() {
        OrderPaymentCommand cmd = new OrderPaymentCommand(new OrderNo("1234"), "paymentID-1234", OrderPaymentCommand.Status.COMPLETED);
        Order order = Order.builder()
                .orderNo(cmd.orderNo())
                .status(OrderStatus.PREPARING)
                .build();
        Mockito.when(orderQueryRepository.findById(cmd.orderNo())).thenReturn(Optional.of(order));
        assertThrows(OrderDomainException.class, () -> {
            paymentEventListener.paymentCompleted(cmd);
        });

    }






    @Test
    void paymentCanceled_Success() {
        // Given
        OrderPaymentCommand cmd = new OrderPaymentCommand(new OrderNo("1234"), "paymentID-1234", OrderPaymentCommand.Status.CANCELLED);
        Order order = Order.builder()
                .orderNo(cmd.orderNo())
                .status(OrderStatus.PREPARING)
                .build();
        Mockito.when(orderQueryRepository.findById(cmd.orderNo())).thenReturn(Optional.of(order));


        paymentEventListener.paymentCanceled(cmd);
        Mockito.verify(orderCommandRepository, Mockito.times(1)).save(order);
        Mockito.verify(orderEventPublisher, Mockito.times(1)).publishOrderCanceledEvent(Mockito.any(OrderCanceledEvent.class));

    }

    /**
     * 출고 된 경우 취소
     */
    @Test
    void paymentCanceled_Fail_Status_SHIPMENT(){
        OrderPaymentCommand cmd = new OrderPaymentCommand(new OrderNo("1234"), "paymentID-1234", OrderPaymentCommand.Status.CANCELLED);
        Order order = Order.builder()
                .orderNo(cmd.orderNo())
                .status(OrderStatus.SHIPMENT)
                .build();

        Mockito.when(orderQueryRepository.findById(cmd.orderNo())).thenReturn(Optional.of(order));
        assertThrows(OrderDomainException.class, () -> {
            paymentEventListener.paymentCanceled(cmd);
        });
    }
}