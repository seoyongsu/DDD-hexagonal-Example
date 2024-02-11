package org.example.hexagonalexample.interfaces.event;

import lombok.extern.slf4j.Slf4j;
import org.example.hexagonalexample.application.ports.in.events.PaymentEventListener;
import org.example.hexagonalexample.application.ports.in.events.dto.OrderPaymentCommand;
import org.example.hexagonalexample.domain.order.vo.OrderNo;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Example!!!  listener
 * TODO : kafka change!!!!
 */
@Component
@Slf4j
public class RedisPaymentMessageListener implements MessageListener {

    private final PaymentEventListener paymentEventListener;

    public RedisPaymentMessageListener(PaymentEventListener paymentEventListener) {
        this.paymentEventListener = paymentEventListener;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        // publish payment orderNo
        String orderNo = message.toString();
        OrderPaymentCommand orderPaymentCommand = new OrderPaymentCommand(
                new OrderNo(orderNo),
                "Payment ID",
                OrderPaymentCommand.Status.COMPLETED
        );
        paymentEventListener.paymentCompleted(orderPaymentCommand);
        log.info("message : {}",message);
        log.info("message Body : {} ",message.getBody());
    }
}
