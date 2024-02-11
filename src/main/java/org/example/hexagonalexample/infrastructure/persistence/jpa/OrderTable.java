package org.example.hexagonalexample.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.example.hexagonalexample.domain.order.vo.Address;
import org.example.hexagonalexample.domain.order.vo.Customer;
import org.example.hexagonalexample.domain.order.vo.OrderItem;
import org.example.hexagonalexample.domain.order.vo.OrderStatus;

import java.util.List;

@Getter
@Entity
@Table(name = "tbl_order")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderTable {
    @Id
    private String orderNo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tbl_order_items")
    private List<OrderItem> orderItems;

    @Setter
    private OrderStatus status;

    @Embedded
    private Customer customer;

    @Embedded
    @Setter
    private Address deliveryAddress;


}
