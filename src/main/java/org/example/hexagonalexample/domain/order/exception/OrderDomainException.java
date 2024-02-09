package org.example.hexagonalexample.domain.order.exception;

/**
 * Domain 규칙 예외
 */
public class OrderDomainException extends RuntimeException {
    public OrderDomainException(String message) {
        super(message);
    }
}
