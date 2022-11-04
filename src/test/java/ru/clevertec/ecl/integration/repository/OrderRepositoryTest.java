package ru.clevertec.ecl.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.entity.Order;
import ru.clevertec.ecl.integration.IntegrationTestBase;
import ru.clevertec.ecl.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.clevertec.ecl.dataForTest.OrderForTest.*;

@RequiredArgsConstructor
public class OrderRepositoryTest extends IntegrationTestBase {

    private final OrderRepository orderRepository;

    @Test
    void findAllTest() {
        List<Order> actual = orderRepository.findAll(pageable()).getContent();
        assertEquals(orders(), actual);
    }

    @Test
    void findAllByUserIdTest() {
        List<Order> actual = orderRepository.findAllByUserId(3L, pageable());
        List<Order> expected = Arrays.asList(order1(), order3());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        Optional<Order> optional = orderRepository.findById(1L);
        optional.ifPresent(actual -> assertEquals(order1(), actual));
    }
}
