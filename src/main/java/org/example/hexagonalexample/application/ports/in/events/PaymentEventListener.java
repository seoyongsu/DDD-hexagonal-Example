package org.example.hexagonalexample.application.ports.in.events;

import org.example.hexagonalexample.application.ports.in.events.dto.OrderPaymentCommand;

/**
 * 결제 Event Listener
 *
 * @author seoyongsu
 */
public interface PaymentEventListener {

    /**
     * 결제 완료 처리
     */
    void paymentCompleted(OrderPaymentCommand cmd);

    /**
     * 결제 취소 처리
     */
    void paymentCanceled(OrderPaymentCommand cmd);

}
