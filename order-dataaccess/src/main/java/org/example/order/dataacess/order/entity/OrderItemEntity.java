package org.example.order.dataacess.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Table(name = "tbl_order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity {

    @Id
    private Long id;

    @ManyToOne
    private OrderEntity order;
    private String itemId;
    private int quantity;


    public OrderItemEntity(OrderEntity order, String itemId, int quantity) {
        this.order = order;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
