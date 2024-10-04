package org.example.order.domain.vo;

import org.example.order.domain.Vo;

/**
 * 주문 상태
 *
 * @author seoyongsu
 */
public enum OrderStatus implements Vo<OrderStatus> {
    PAYMENT_WAITING("결제 대기"),
    PREPARING("준비 중"),
    SHIPMENT("출고"),
    DELIVERING("배송 중"),
    DELIVERY_COMPLETED("배송 완료"),
    CANCELED("취소 완료"),
    ;

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

    @Override
    public boolean sameValueAs(OrderStatus other) {
        return this.equals(other);
    }
}
