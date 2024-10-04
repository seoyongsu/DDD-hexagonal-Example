package org.example.order.message.listener;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.example.order.application.ports.in.events.PaymentEventListener;
import org.example.order.application.ports.in.events.dto.OrderPaymentCommand;
import org.example.order.domain.vo.OrderNo;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;


/**
 * Payment Event Listener Example
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
        log.info("message : {}",message);
        log.info("message Body : {} ",message.getBody());

        JsonElement jsonElement = JsonParser.parseString(message.toString());
        if(jsonElement.isJsonObject()){
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String orderNo = jsonObject.get("orderNo").getAsString();
            String payment = jsonObject.get("paymentId").getAsString();
            OrderPaymentCommand.Status status = OrderPaymentCommand.Status.valueOf(jsonObject.get("paymentStatus").getAsString());
            OrderPaymentCommand orderPaymentCommand = new OrderPaymentCommand(
                    new OrderNo(orderNo),
                    payment,
                    status
            );
            switch (status){
                case COMPLETED ->  paymentEventListener.paymentCompleted(orderPaymentCommand);
                case CANCELLED ->  paymentEventListener.paymentCanceled(orderPaymentCommand);
                default -> log.info("ETC....");
            }
        }
    }
}