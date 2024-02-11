package org.example.hexagonalexample.application.ports.in.usecase.dto;

import org.example.hexagonalexample.domain.order.vo.Address;
import org.example.hexagonalexample.domain.order.vo.Customer;
import org.example.hexagonalexample.domain.order.vo.OrderItem;

import java.util.List;

/**
 * 주문 생성 Command
 * @param orderItems        주문 Items 목록
 * @param customer          고객
 * @param deliveryAddress   배송 주소
 *
 * @author seoyongsu
 */
public record OrderCommand(
        List<OrderItem> orderItems,
        Customer customer,
        Address deliveryAddress
) {
    public OrderCommand {
        if(orderItems == null || orderItems.isEmpty())
            throw new IllegalArgumentException("OrderItems cannot null or empty for create order command!");
        if(customer == null)
            throw new IllegalArgumentException("Orderer cannot be null for create order command!");
        if(deliveryAddress == null)
            throw new IllegalArgumentException("DeliveryAddress cannot be null for create order command");
    }


}
