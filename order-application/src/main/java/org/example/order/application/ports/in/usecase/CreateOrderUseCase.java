package org.example.order.application.ports.in.usecase;

import org.example.order.application.ports.in.usecase.dto.OrderResponse;
import org.example.order.application.ports.in.usecase.dto.OrderCommand;

/**
 * 주문 생성 UseCase
 */
public interface CreateOrderUseCase {
    OrderResponse createOrder(OrderCommand cmd);
}
