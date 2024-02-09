package org.example.hexagonalexample.domain.order;

import org.example.hexagonalexample.domain.order.entity.Order;
import org.example.hexagonalexample.domain.order.event.OrderCanceledEvent;
import org.example.hexagonalexample.domain.order.event.OrderCreateEvent;
import org.example.hexagonalexample.domain.order.event.OrderPaidEvent;

/**
 * 도메인 서비스
 * <pre>
 *     도메인에 대한 비지니스 처리
 *     TODO : 도메인 정책에 대한 확장성 위해 선언만 해놓음.
 * </pre>
 *
 * @author seoyongsu
 */
public interface OrderDomainService {

    /**
     * 주문 생성 도메인 규칙 적용
     */
    default OrderCreateEvent validateAndCreateOrder(Order order){
        //Example
        order.validateOrder();
        return new OrderCreateEvent(order);
    }

    default OrderPaidEvent payOrder(Order order){
        //Example
        order.payment();
        return new OrderPaidEvent(order);
    }

    default OrderCanceledEvent cancelOrder(Order order){
        //Example
        order.cancel();
        return new OrderCanceledEvent(order);
    }
}
