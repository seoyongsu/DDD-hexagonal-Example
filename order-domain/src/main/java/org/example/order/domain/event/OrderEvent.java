package org.example.order.domain.event;


import org.example.order.domain.Event;
import org.example.order.domain.entity.Order;

import java.time.Instant;

/**
 * 추상 Order Event
 *
 * @author seoyongsu
 */
public abstract class OrderEvent implements Event {
    private final Order order;
    private final Instant timestamp;

    protected OrderEvent(Order order) {
        this.order = order;
        this.timestamp = Instant.now();
    }

    public Order getOrder(){
        return this.order;
    }

    public Instant getTimestamp(){
        return this.timestamp;
    }


}
