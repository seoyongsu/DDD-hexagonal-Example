package org.example.order.domain;

import org.example.order.domain.entity.Order;
import org.example.order.domain.exception.OrderDomainException;
import org.example.order.domain.vo.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDomainServiceTest {
    @Test
    void validateAndCreateOrder() {
        Order order = new Order(
                List.of(new OrderItem("1", 1)),
                new Customer("hello", "World"),
                new Address("01234","서울시",null, null,null,null)
        );
        order.validateOrder();
        assertEquals(order.getStatus(), OrderStatus.PAYMENT_WAITING);

        Order order1 = Order.builder()
                .orderNo(order.getId())
                .build();
        assertThrows(OrderDomainException.class, order1::validateOrder);

        Order order2 = new Order(
                null,
                new Customer("hello", "World"),
                new Address("01234","서울시",null, null,null,null)
        );
        assertThrows(OrderDomainException.class, order2::validateOrder);


        Order order3 = new Order(
                Collections.emptyList(),
                new Customer("hello", "World"),
                new Address("01234","서울시",null, null,null,null)
        );
        assertThrows(OrderDomainException.class, order3::validateOrder);

        Order order4 = new Order(
                List.of(new OrderItem("1", 1)),
                null,
                new Address("01234","서울시",null, null,null,null)
        );
        assertThrows(OrderDomainException.class, order4::validateOrder);


        Order order5 = new Order(
                List.of(new OrderItem("1", 1)),
                new Customer("hello", "World"),
                null
        );
        assertThrows(OrderDomainException.class, order5::validateOrder);
    }

    @Test
    void payOrder() {
        Order order = new Order(new OrderNo("order-1234"), List.of(new OrderItem("1", 1)),
                new Customer("hello", "World"),
                new Address("01234","서울시",null, null,null,null),
                OrderStatus.PAYMENT_WAITING
        );
        order.payment();
        assertEquals(order.getStatus(), OrderStatus.PREPARING);


        Order order1 = new Order(new OrderNo("order-1234"), List.of(new OrderItem("1", 1)),
                new Customer("hello", "World"),
                new Address("01234","서울시",null, null,null,null),
                OrderStatus.PREPARING
        );
        assertThrows(OrderDomainException.class, order1::payment);

    }

    @Test
    void cancelOrder() {
        Order order = new Order(new OrderNo("order-1234"), List.of(new OrderItem("1", 1)),
                new Customer("hello", "World"),
                new Address("01234","서울시",null, null,null,null),
                OrderStatus.PREPARING
        );
        order.cancel();
        assertEquals(order.getStatus(), OrderStatus.CANCELED);


        Order order1 = new Order(new OrderNo("order-1234"), List.of(new OrderItem("1", 1)),
                new Customer("hello", "World"),
                new Address("01234","서울시",null, null,null,null),
                OrderStatus.PAYMENT_WAITING
        );
        assertThrows(OrderDomainException.class, order1::cancel);

        Order order2 = new Order(new OrderNo("order-1234"), List.of(new OrderItem("1", 1)),
                new Customer("hello", "World"),
                new Address("01234","서울시",null, null,null,null),
                OrderStatus.DELIVERING
        );
        assertThrows(OrderDomainException.class, order2::cancel);
    }



}