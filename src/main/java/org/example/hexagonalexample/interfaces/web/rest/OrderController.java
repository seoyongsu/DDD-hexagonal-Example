package org.example.hexagonalexample.interfaces.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.example.hexagonalexample.application.ports.in.usecase.CreateOrderUseCase;
import org.example.hexagonalexample.application.ports.in.usecase.TrackOrderUseCase;
import org.example.hexagonalexample.application.ports.in.usecase.dto.OrderCommand;
import org.example.hexagonalexample.application.ports.in.usecase.dto.OrderResponse;
import org.example.hexagonalexample.domain.order.vo.OrderNo;
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
    public ResponseEntity<?> orderRequest(@RequestBody OrderCommand request){
        OrderResponse response = createOrderUseCase.createOrder(request);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("{orderNo}")
    public ResponseEntity<?> getOrder(@PathVariable String orderNo){
        OrderResponse response = trackOrderUseCase.trackOrder(new OrderNo(orderNo));
        return ResponseEntity.ok().body(response);
    }

}
