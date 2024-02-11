package org.example.hexagonalexample.application.ports.in.usecase;

import org.example.hexagonalexample.application.ports.in.usecase.dto.OrderCommand;
import org.example.hexagonalexample.application.ports.in.usecase.dto.OrderResponse;

/**
 * 주문 생성 UseCase
 */
public interface CreateOrderUseCase {
    OrderResponse createOrder(OrderCommand cmd);
}
