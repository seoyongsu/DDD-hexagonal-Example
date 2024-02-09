package org.example.hexagonalexample.domain.order.vo;

import org.example.hexagonalexample.domain.core.Vo;

/**
 * 주문자
 * @param customerId    고객 ID
 * @param name          고객 이름
 *
 * @author seoyongsu
 */
public record Customer(
        String customerId,
        String name
) implements Vo<Customer> {
    @Override
    public boolean sameValueAs(Customer other) {
        return this.customerId.equals(other.customerId);
    }
}
