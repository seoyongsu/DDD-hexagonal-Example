package org.example.order.domain.exception;

import org.example.order.domain.DomainException;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException() {
        super("Order Not Found Exception...");
    }

}
