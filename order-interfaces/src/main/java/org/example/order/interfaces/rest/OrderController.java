package org.example.order.interfaces.rest;

import lombok.extern.slf4j.Slf4j;
import org.example.order.application.ports.in.usecase.CreateOrderUseCase;
import org.example.order.application.ports.in.usecase.TrackOrderUseCase;
import org.example.order.application.ports.in.usecase.dto.OrderCommand;
import org.example.order.application.ports.in.usecase.dto.OrderResponse;
import org.example.order.domain.vo.OrderNo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final TrackOrderUseCase trackOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, TrackOrderUseCase trackOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.trackOrderUseCase = trackOrderUseCase;
    }


    @PostMapping
    public ResponseEntity<OrderResponse> orderRequest(@RequestBody OrderCommand request) {
        OrderResponse response = createOrderUseCase.createOrder(request);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "{orderNo}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String orderNo) {
        try {
            OrderResponse response = trackOrderUseCase.trackOrder(new OrderNo(orderNo));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error("Error tracking order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
