package org.example.hexagonalexample.domain.order.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Order Not Found...");
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
