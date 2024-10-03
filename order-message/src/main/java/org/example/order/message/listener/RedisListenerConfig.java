package org.example.order.message.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * 해당 모듈은 Main Class가 없기 때문에
 * Configuration 어노테이션 대신 Component로 의존성관리
 */
@Component
//@Configuration
public class RedisListenerConfig {


    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory,
            RedisPaymentMessageListener redisPaymentMessageListener
    ){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(
                new MessageListenerAdapter(redisPaymentMessageListener),
                new ChannelTopic("payment")
        );
        return container;
    }
}
