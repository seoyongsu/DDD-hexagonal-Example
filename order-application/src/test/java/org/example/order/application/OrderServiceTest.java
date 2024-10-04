package org.example.order.application;

import org.example.order.application.ports.in.usecase.dto.OrderCommand;
import org.example.order.application.ports.in.usecase.dto.OrderResponse;
import org.example.order.application.ports.out.events.OrderEventPublisher;
import org.example.order.application.ports.out.repository.OrderCommandRepository;
import org.example.order.application.ports.out.repository.OrderQueryRepository;
import org.example.order.domain.OrderDomainService;
import org.example.order.domain.entity.Order;
import org.example.order.domain.event.OrderCreateEvent;
import org.example.order.domain.exception.OrderNotFoundException;
import org.example.order.domain.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderQueryRepository orderQueryRepository;

    @Mock
    private OrderCommandRepository orderCommandRepository;

    @Mock
    private OrderEventPublisher paymentEventPublisher;
    @InjectMocks
    private OrderService orderService;



    @Test
    @Transactional
    public void testCreateOrder() {
        OrderCommand cmd = new OrderCommand(
                List.of(new OrderItem("1", 10)),
                new Customer("YS","용수"),
                new Address("123", "서울", "서대문","","333","")
        );

        Order order = Order.builder()
                .orderNo(new OrderNo())
                .orderItems(cmd.orderItems())
                .customer(cmd.customer())
                .deliveryAddress(cmd.deliveryAddress())
                .status(OrderStatus.PAYMENT_WAITING)
                .build();

        Mockito.when(orderCommandRepository.save(Mockito.any(Order.class))).thenReturn(order);

        OrderResponse response = orderService.createOrder(cmd);

        // Assert
        assertNotNull(response);
        assertEquals(order.getId().getNo(), response.getOrderNo());
        Mockito.verify(orderCommandRepository, Mockito.times(1)).save(Mockito.any(Order.class));
        Mockito.verify(paymentEventPublisher, Mockito.times(1)).publishOrderCreateEvent(Mockito.any(OrderCreateEvent.class));
    }

//    @Test
    void trackOrderSuccessTest() {
        OrderNo orderNo = new OrderNo("1234");
        Order order = Order.builder()
                .orderNo(orderNo)
                .orderItems(List.of(new OrderItem("1", 10)))
                .customer(new Customer("YS","용수"))
                .deliveryAddress(new Address("123", "서울", "서대문","","333",""))
                .status(OrderStatus.PAYMENT_WAITING)
                .build();


        // Given
        Mockito.when(orderQueryRepository.findById(orderNo)).thenReturn(Optional.of(order));

        OrderResponse response = orderService.trackOrder(orderNo);

        assertNotNull(response);
        assertEquals(orderNo.getNo(), response.getOrderNo());
        Mockito.verify(orderQueryRepository, Mockito.times(1)).findById(orderNo);

    }

    @Test
    void trackOrderNotFoundTest(){
        OrderNo orderNo = new OrderNo("1234");
        Mockito.when(orderQueryRepository.findById(orderNo)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(OrderNotFoundException.class, () -> orderService.trackOrder(orderNo));
        Mockito.verify(orderQueryRepository, Mockito.times(1)).findById(orderNo);
    }
}