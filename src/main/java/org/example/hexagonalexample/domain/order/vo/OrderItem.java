package org.example.hexagonalexample.domain.order.vo;

import org.example.hexagonalexample.domain.core.Vo;

/**
 * 주문 상품
 * @param itemId    상품 ID
 * @param quantity  상품 수량
 *
 * @author seoyongsu
 */
public record OrderItem(
        String itemId,
        int quantity

) implements Vo<OrderItem> {

    @Override
    public boolean sameValueAs(OrderItem other) {
        return this.equals(other);
    }
}
