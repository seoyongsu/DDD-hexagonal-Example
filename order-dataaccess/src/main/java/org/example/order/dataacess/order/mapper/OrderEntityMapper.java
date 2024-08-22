package org.example.order.dataacess.order.mapper;

import org.example.order.dataacess.order.entity.OrderEntity;
import org.example.order.dataacess.order.entity.OrderItemEntity;
import org.example.order.domain.entity.Order;
import org.example.order.domain.vo.OrderItem;
import org.example.order.domain.vo.OrderNo;

import java.util.List;
import java.util.stream.Collectors;

public class OrderEntityMapper {

    /**
     * Domain entity -> Jpa Entity
     * @param order
     * @return OrderEntity
     */
    public static OrderEntity toJpaEntity(Order order){
        OrderEntity orderEntity = OrderEntity.builder()
                .orderNo(order.getId().getNo())
                .status(order.getStatus())
                .customer(order.getCustomer())
                .address(order.getDeliveryAddress())
                .build();

        List<OrderItemEntity> orderItemEntities = order.getOrderItems()
                .stream().map(v-> new OrderItemEntity(orderEntity, v.itemId(), v.quantity()))
                .toList();
        orderEntity.setOrderItems(orderItemEntities);
        return orderEntity;
    }

    /**
     * JPA Entity to -> Domain Entity
     * @param orderEntity
     * @return
     * TODO :
     */
    public static Order toEntity(OrderEntity orderEntity){
        return Order.builder()
                .orderNo(new OrderNo(orderEntity.getOrderNo()))
                .orderItems(
                        orderEntity.getOrderItems()
                        .stream().map(v -> new OrderItem(v.getItemId(), v.getQuantity()))
                        .collect(Collectors.toList())
                )
                .customer(orderEntity.getCustomer())
                .deliveryAddress(orderEntity.getAddress())
                .status(orderEntity.getStatus())
                .build();
    }



}
