package org.example.hexagonalexample.application.ports.in.usecase;

import org.example.hexagonalexample.application.ports.in.usecase.dto.OrderResponse;
import org.example.hexagonalexample.domain.order.vo.OrderNo;

/**
 * 주문 추적 UseCase
 */
public interface TrackOrderUseCase {
    OrderResponse trackOrder(OrderNo orderNo);
}
