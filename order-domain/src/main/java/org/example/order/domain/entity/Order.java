package org.example.order.domain.entity;


import org.example.order.domain.AggregateRoot;
import org.example.order.domain.exception.OrderDomainException;
import org.example.order.domain.vo.*;

import java.util.List;
import java.util.Objects;

/**
 * 주문 Aggregate
 *
 * @author seoyongsu
 */
public class Order implements AggregateRoot<OrderNo> {

    private final OrderNo orderNo;
    private final List<OrderItem> orderItems;
    private final Customer customer;
    private Address deliveryAddress;
    private OrderStatus status;

    /**
     * 주문 검증
     */
    public void validateOrder(){
        if(this.orderNo == null)
            throw new OrderDomainException("Order No cannot be null!");
        if(this.orderItems == null || this.orderItems.isEmpty())
            throw new OrderDomainException("Order Item cannot be null or empty!");
        if(this.customer == null)
            throw new OrderDomainException("Orderer cannot be null or empty!");
        if(this.deliveryAddress == null)
            throw new OrderDomainException("DeliveryAddress cannot be null or empty!");
        if(!OrderStatus.PAYMENT_WAITING.equals(status))
            throw new OrderDomainException("Order is not in correct state for initialize operation!");
    }

    /**
     * 결제 처리
     */
    public void payment(){
        if(!OrderStatus.PAYMENT_WAITING.equals(status)){
            //결제 불가
            throw new OrderDomainException("Order is not in correct state for payment operation!");
        }
        this.status = OrderStatus.PREPARING;
    }

    /**
     * 배송지 변경
     */
    public void changeDeliveryAddress(Address newAddress){
        if(!OrderStatus.PAYMENT_WAITING.equals(status) && !OrderStatus.PREPARING.equals(status)){
            //배송지 변경 불가
            throw new OrderDomainException("Order is not in correct state for changeDeliveryAddress operation!");
        }
        this.deliveryAddress = newAddress;
    }

    /**
     * 주문 취소
     */
    public void cancel(){
        if(!OrderStatus.PREPARING.equals(status)){
            //Order Cancel Exception
            throw new OrderDomainException("Order is not in correct state for cancel operation!");
        }
        this.status = OrderStatus.CANCELED;
    }


    @Override
    public OrderNo getId() {
        return this.orderNo;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderNo, order.orderNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNo);
    }


    public Order(List<OrderItem> orderItems, Customer customer, Address deliveryAddress) {
        this.orderNo = new OrderNo();
        this.orderItems = orderItems;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.status = OrderStatus.PAYMENT_WAITING;
    }
    public Order(OrderNo orderNo, List<OrderItem> orderItems, Customer customer, Address deliveryAddress, OrderStatus status) {
        this.orderNo = orderNo;
        this.orderItems = orderItems;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
    }
    /**
     * POJO 방식으로 인해 Builder 직접 생성.
     */
    private Order(Builder builder) {
        this.orderNo = builder.orderNo;
        this.orderItems = builder.orderItems;
        this.customer = builder.customer;
        this.status = builder.status;
        this.deliveryAddress = builder.deliveryAddress;
    }
    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private OrderNo orderNo;
        private List<OrderItem> orderItems;
        private Customer customer;
        private OrderStatus status;
        private Address deliveryAddress;
        private Builder() {
        }

        public Builder orderNo(OrderNo orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        public Builder orderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder deliveryAddress(Address deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }
        public Order build() {
            return new Order(this);
        }
    }
}
