package ru.clevertec.ecl.dataForTest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.dto.OrderDto;
import ru.clevertec.ecl.dto.ReadOrderDto;
import ru.clevertec.ecl.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ru.clevertec.ecl.dataForTest.GiftCertificateForTest.*;
import static ru.clevertec.ecl.dataForTest.UserForTest.*;

public class OrderForTest {

    public static Order order1() {
        return Order.builder()
                .id(1L)
                .cost(BigDecimal.valueOf(115.15))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .user(user3())
                .giftCertificate(giftCertificate1())
                .build();
    }

    public static Order order2() {
        return Order.builder()
                .id(2L)
                .cost(BigDecimal.valueOf(75.75))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .user(user2())
                .giftCertificate(giftCertificate2())
                .build();
    }

    public static Order order3() {
        return Order.builder()
                .id(3L)
                .cost(BigDecimal.valueOf(102.05))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .user(user3())
                .giftCertificate(giftCertificate3())
                .build();
    }

    public static Order order4() {
        return Order.builder()
                .id(4L)
                .cost(BigDecimal.valueOf(55.55))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .user(user1())
                .giftCertificate(giftCertificate4())
                .build();
    }

    public static Order order1WithoutId() {
        return Order.builder()
                .cost(BigDecimal.valueOf(115.15))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .user(user3())
                .giftCertificate(giftCertificate1())
                .build();
    }

    public static Order order1ForCreate() {
        return Order.builder()
                .id(1L)
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .build();
    }

    public static Order order1ForCreateWithCost() {
        return Order.builder()
                .id(1L)
                .cost(BigDecimal.valueOf(115.15))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .build();
    }

    public static OrderDto orderDto1() {
        return OrderDto.builder()
                .id(1L)
                .cost(BigDecimal.valueOf(115.15))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .userId(3L)
                .giftCertificateId(1L)
                .build();
    }

    public static OrderDto orderDto2() {
        return OrderDto.builder()
                .id(2L)
                .cost(BigDecimal.valueOf(75.75))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .userId(2L)
                .giftCertificateId(2L)
                .build();
    }

    public static OrderDto orderDto3() {
        return OrderDto.builder()
                .id(3L)
                .cost(BigDecimal.valueOf(102.05))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .userId(3L)
                .giftCertificateId(3L)
                .build();
    }

    public static OrderDto orderDto4() {
        return OrderDto.builder()
                .id(4L)
                .cost(BigDecimal.valueOf(55.55))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .userId(1L)
                .giftCertificateId(4L)
                .build();
    }

    public static ReadOrderDto readOrderDtoForCreate() {
        return ReadOrderDto.builder()
                .userId(3L)
                .giftCertificateId(1L)
                .build();
    }

    public static OrderDto orderDtoForCreate() {
        return OrderDto.builder()
                .id(5L)
                .cost(BigDecimal.valueOf(115.15))
                .purchaseTimestamp(LocalDateTime.now().withNano(0))
                .userId(3L)
                .giftCertificateId(1L)
                .build();
    }

//    public static ReadOrderDto orderDtoForCreateOrderWithCost() {
//        return ReadOrderDto.builder()
//                .userId(3L)
//                .cost(BigDecimal.valueOf(115.15))
//                .giftCertificateId(1L)
//                .build();
//    }

    public static ReadOrderDto readOrderDtoForCreateOrderWithNotExistUserId() {
        return ReadOrderDto.builder()
                .userId(8L)
                .giftCertificateId(1L)
                .build();
    }

    public static ReadOrderDto readOrderDtoForCreateOrderWithNotExistCertificateId() {
        return ReadOrderDto.builder()
                .userId(1L)
                .giftCertificateId(30L)
                .build();
    }

    public static List<Order> orders() {
        return Arrays.asList(order1(), order2(), order3(), order4());
    }

    public static List<OrderDto> ordersDto() {
        return Arrays.asList(orderDto1(), orderDto2(), orderDto3(), orderDto4());
    }

    public static Pageable pageable() {
        return PageRequest.of(0, 20);
    }
}
