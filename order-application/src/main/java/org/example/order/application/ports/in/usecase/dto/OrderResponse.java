package org.example.order.application.ports.in.usecase.dto;

import lombok.Getter;
import org.example.order.domain.entity.Order;
import org.example.order.domain.vo.Address;
import org.example.order.domain.vo.OrderItem;
import org.example.order.domain.vo.OrderStatus;

import java.util.List;

/**
 * 주문 Response DTO
 */
@Getter
public class OrderResponse {
    private final String orderNo;
    private final List<OrderItem> orderItems;
    private final OrderStatus orderStatus;
    private final Address deliveryAddress;

    public OrderResponse(Order order) {
        this.orderNo = order.getId().getNo();
        this.orderItems = order.getOrderItems();
        this.orderStatus = order.getStatus();
        this.deliveryAddress = order.getDeliveryAddress();
    }

}
