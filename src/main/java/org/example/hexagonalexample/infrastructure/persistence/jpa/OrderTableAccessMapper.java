package org.example.hexagonalexample.infrastructure.persistence.jpa;


import org.example.hexagonalexample.domain.order.entity.Order;
import org.example.hexagonalexample.domain.order.vo.OrderNo;

class OrderTableAccessMapper {

    static OrderTable toTable(Order order){
        return OrderTable.builder()
                .orderNo(order.getId().getNo())
                .orderItems(order.getOrderItems())
                .status(order.getStatus())
                .customer(order.getCustomer())
                .deliveryAddress(order.getDeliveryAddress())
                .build();

    }

    static Order toEntity(OrderTable orderTable){
        return Order.builder()
                .orderNo(new OrderNo(orderTable.getOrderNo()))
                .orderItems(orderTable.getOrderItems())
                .deliveryAddress(orderTable.getDeliveryAddress())
                .customer(orderTable.getCustomer())
                .status(orderTable.getStatus())
                .build();
    }


}
