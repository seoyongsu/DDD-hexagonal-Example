package org.example.order.application.ports.in.usecase;

import org.example.order.application.ports.in.usecase.dto.OrderResponse;
import org.example.order.domain.vo.OrderNo;

/**
 * 주문 추적 UseCase
 */
public interface TrackOrderUseCase {
    OrderResponse trackOrder(OrderNo orderNo);
}
