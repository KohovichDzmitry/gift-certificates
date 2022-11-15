package ru.clevertec.ecl.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.dto.OrderDto;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.integration.IntegrationTestBase;
import ru.clevertec.ecl.repository.OrderRepository;
import ru.clevertec.ecl.service.OrderService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.clevertec.ecl.dataForTest.OrderForTest.*;

@RequiredArgsConstructor
public class OrderServiceImplTest extends IntegrationTestBase {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @Test
    void findAllTest() {
        List<OrderDto> actual = orderService.findAll(pageable());
        assertEquals(ordersDto(), actual);
    }

    @Test
    void findAllByUserIdTest() {
        List<OrderDto> actual = orderService.findAllByUserId(3L, pageable());
        List<OrderDto> expected = Arrays.asList(orderDto3(), orderDto1());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        OrderDto actual = orderService.findById(1L);
        assertEquals(orderDto1(), actual);
    }

    @Test
    void findByIdNegativeTest() {
        assertThrows(EntityNotFoundException.class, () -> orderService.findById(8L));
    }

    @Test
    void createOrderTest() {
        OrderDto actual = orderService.createOrder(readOrderDtoForCreate());
        orderRepository.flush();
        assertEquals(orderDtoForCreate(), actual);
    }

    @Test
    void createOrderNegativeTest() {
        assertThrows(EntityNotFoundException.class,
                () -> orderService.createOrder(readOrderDtoForCreateOrderWithNotExistCertificateId()));
    }

    @Test
    void createOrderNegativeTest2() {
        assertThrows(EntityNotFoundException.class,
                () -> orderService.createOrder(readOrderDtoForCreateOrderWithNotExistUserId()));
    }
}
