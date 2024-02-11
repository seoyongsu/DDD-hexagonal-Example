package org.example.hexagonalexample.application.ports.in.events.dto;

import lombok.Getter;
import org.example.hexagonalexample.domain.order.vo.OrderNo;

/**
 * 주문 결제 Command
 * <pre>
 *     consumer or subscript
 * </pre>
 *
 * @param orderNo       주문번호
 * @param paymentId     결제 ID
 * @param paymentStatus 결제 상태
 *
 * @author seoyongsu
 */
public record OrderPaymentCommand(
        OrderNo orderNo,
        String paymentId,
        Status paymentStatus
) {


    /**
     * 결제 결과 status
     */
    @Getter
    public enum Status {
        COMPLETED, CANCELLED, FAILED
    }


}
