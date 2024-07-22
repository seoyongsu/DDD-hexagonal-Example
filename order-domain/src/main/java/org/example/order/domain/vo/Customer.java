package org.example.order.domain.vo;

import org.example.order.domain.Vo;

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
