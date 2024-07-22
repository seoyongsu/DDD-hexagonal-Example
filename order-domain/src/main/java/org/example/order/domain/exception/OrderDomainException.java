package org.example.order.domain.exception;

import org.example.order.domain.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }
}
