package org.example.order.dataacess.order.repository;

import org.example.order.dataacess.order.entity.OrderEntity;
import org.example.order.dataacess.order.entity.OrderItemEntity;
import org.example.order.dataacess.order.mapper.OrderEntityMapper;
import org.example.order.domain.vo.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaOrderRepositoryTest {

    @Autowired
    private JpaOrderRepository jpaOrderRepository;

    private final String orderNo = "Hello-Test";

    @Test
    void insertTest(){

        OrderEntity orderEntity = OrderEntity.builder()
                .orderNo(orderNo)
                .address(new Address("123", "서울", "서대문","","333",""))
                .customer(new Customer("YS","용수"))
                .status(OrderStatus.PAYMENT_WAITING)
                .build();
        List<OrderItemEntity> orderItemEntities = List.of(
                new OrderItemEntity(orderEntity, "1",1)
        );
        orderEntity.setOrderItems(orderItemEntities);

        //저장 Test
        OrderEntity savedOrderEntity = jpaOrderRepository.save(orderEntity);
        //insert 검증
        assertNotNull(savedOrderEntity);
        assertEquals(orderEntity.getOrderNo(), savedOrderEntity.getOrderNo());
        assertEquals(orderEntity.getCustomer(), savedOrderEntity.getCustomer());

        // 조회 Test
        Optional<OrderEntity> findEntity = jpaOrderRepository.findById(orderNo);
        // 조회 검증
        assertTrue(findEntity.isPresent());
        assertEquals(orderEntity.getOrderNo(), findEntity.get().getOrderNo());


        //삭제 Test
        jpaOrderRepository.deleteById(orderNo);

        // 삭제 확인
        Optional<OrderEntity> foundOrderEntity = jpaOrderRepository.findById(orderNo);
        assertFalse(foundOrderEntity.isPresent());
    }

}