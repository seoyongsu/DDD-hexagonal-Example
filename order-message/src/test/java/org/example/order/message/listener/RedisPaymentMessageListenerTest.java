package org.example.order.message.listener;

import org.example.order.application.ports.in.events.PaymentEventListener;
import org.example.order.application.ports.in.events.dto.OrderPaymentCommand;
import org.example.order.domain.vo.OrderNo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.connection.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class RedisPaymentMessageListenerTest {


    @Mock
    private PaymentEventListener paymentEventListener;

    @InjectMocks
    private RedisPaymentMessageListener redisPaymentMessageListener;

    @Test
    void paymentCompletedTest() {
        // Arrange
        String jsonMessage = """
        {
            "orderNo":"ORDER12345",
            "paymentId":"PAYMENT67890",
            "paymentStatus":"COMPLETED"
        }
        """;

        Message message = mock(Message.class);
        when(message.toString()).thenReturn(jsonMessage);
        when(message.getBody()).thenReturn(jsonMessage.getBytes());

        byte[] pattern = new byte[]{};

        // Act
        redisPaymentMessageListener.onMessage(message, pattern);

        // Assert
        ArgumentCaptor<OrderPaymentCommand> captor = ArgumentCaptor.forClass(OrderPaymentCommand.class);
        verify(paymentEventListener, times(1)).paymentCompleted(captor.capture());

        // 전달된 OrderPaymentCommand 객체의 필드 값 검증
        OrderPaymentCommand command = captor.getValue();
        assertNotNull(command);
        assertEquals(new OrderNo("ORDER12345"), command.orderNo());
        assertEquals("PAYMENT67890", command.paymentId());
        assertEquals(OrderPaymentCommand.Status.COMPLETED, command.paymentStatus());
    }

}