package org.example.order.interfaces.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.order.application.ports.in.usecase.CreateOrderUseCase;
import org.example.order.application.ports.in.usecase.TrackOrderUseCase;
import org.example.order.application.ports.in.usecase.dto.OrderCommand;
import org.example.order.application.ports.in.usecase.dto.OrderResponse;
import org.example.order.domain.entity.Order;
import org.example.order.domain.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(OrderControllerTest.class);
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateOrderUseCase createOrderUseCase;

    @MockBean
    private TrackOrderUseCase trackOrderUseCase;

    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper(); // JSON 변환을 위한 ObjectMapper 초기화
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    @Nested
    @DisplayName("POST /orders")
    class CreateOrderTests{

        @Test
        @DisplayName("200 ok")
        void status_200() throws Exception {
            //Given
            OrderItem item1 = new OrderItem("사과",  10);
            OrderItem item2 = new OrderItem("딸기", 20);
            Customer customer = new Customer("hearts-ping","하츄핑");
            Address address = new Address("000","서울","","","","");

            OrderNo orderNo = new OrderNo("Order - 123456");

            OrderCommand requestBody  =  new OrderCommand(List.of(item1, item2), customer, address);
            Order order = new Order(orderNo, List.of(item1, item2), customer, address, OrderStatus.PAYMENT_WAITING);
            OrderResponse expectedResponse = new OrderResponse(order);


            given(createOrderUseCase.createOrder(any(OrderCommand.class))).willReturn(expectedResponse);

            // MVC Test & Then
            mockMvc.perform(post("/orders") // Adjust the URL path as necessary
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestBody)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));

        }

    }

    @Nested
    @DisplayName("GET /orders/{orderNo}")
    class GetOrderTest {
        @Test
        void status_200() throws Exception {
            //Given
            String orderNo = "Order-123456";
            OrderItem item1 = new OrderItem("사과", 10);
            OrderItem item2 = new OrderItem("딸기", 20);
            Customer customer = new Customer("hearts-ping", "하츄핑");
            Address address = new Address("000", "서울", "", "", "", "");
            Order order = new Order(new OrderNo(orderNo), List.of(item1, item2), customer, address, OrderStatus.PAYMENT_WAITING);

            OrderResponse expectedResponse = new OrderResponse(order);

            // Mocking TrackOrderUseCase
            given(trackOrderUseCase.trackOrder(any(OrderNo.class))).willReturn(expectedResponse);

            // When & Then
            mockMvc.perform(get("/orders/{orderNo}", orderNo)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(result -> {
                        System.out.println("Response Status: " + result.getResponse().getStatus());
                        System.out.println("Response Body: " + result.getResponse().getContentAsString());
                    })
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
        }
    }
}