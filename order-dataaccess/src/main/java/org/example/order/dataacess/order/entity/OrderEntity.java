package org.example.order.dataacess.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.order.domain.vo.Address;
import org.example.order.domain.vo.Customer;
import org.example.order.domain.vo.OrderStatus;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "tbl_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderEntity {

    @Id
    private String orderNo;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Setter
    private List<OrderItemEntity> orderItems;

    @Embedded
    @Setter
    private Address address;

    @Enumerated(EnumType.STRING)
    @Setter
    private OrderStatus status;


    @Embedded
    private Customer customer;

    @CreatedDate
    private LocalDateTime createdAt;


}
