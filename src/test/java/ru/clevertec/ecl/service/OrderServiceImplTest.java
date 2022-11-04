package ru.clevertec.ecl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import ru.clevertec.ecl.dto.OrderDto;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.mapper.OrderMapper;
import ru.clevertec.ecl.repository.OrderRepository;
import ru.clevertec.ecl.service.impl.OrderServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static ru.clevertec.ecl.dataForTest.GiftCertificateForTest.giftCertificateDto1;
import static ru.clevertec.ecl.dataForTest.OrderForTest.*;
import static ru.clevertec.ecl.dataForTest.OrderForTest.orderDto1;
import static ru.clevertec.ecl.dataForTest.UserForTest.userDto3;


@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private GiftCertificateService giftCertificateService;

    @Mock
    private UserService userService;
    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void findAllTest() {
        doReturn(new PageImpl<>(orders()))
                .when(orderRepository).findAll(pageable());
        doReturn(ordersDto())
                .when(orderMapper).toDtoList(orders());
        List<OrderDto> actual = orderService.findAll(pageable());
        List<OrderDto> expected = ordersDto();
        assertEquals(expected, actual);
        verify(orderRepository).findAll(pageable());
        verify(orderMapper).toDtoList(orders());
    }

    @Test
    void findAllByUserIdTest() {
        doReturn(Arrays.asList(order1(), order3()))
                .when(orderRepository).findAllByUserId(3L, pageable());
        doReturn(Arrays.asList(orderDto1(), orderDto3()))
                .when(orderMapper).toDtoList(Arrays.asList(order1(), order3()));
        List<OrderDto> actual = orderService.findAllByUserId(3L, pageable());
        List<OrderDto> expected = Arrays.asList(orderDto1(), orderDto3());
        assertEquals(expected, actual);
        verify(orderRepository).findAllByUserId(3L, pageable());
        verify(orderMapper).toDtoList(Arrays.asList(order1(), order3()));
    }

    @Test
    void findByIdTest() {
        doReturn(Optional.of(order1()))
                .when(orderRepository).findById(1L);
        doReturn(orderDto1())
                .when(orderMapper).toDto(order1());
        OrderDto actual = orderService.findById(1L);
        OrderDto expected = orderDto1();
        assertEquals(expected, actual);
        verify(orderRepository).findById(1L);
        verify(orderMapper).toDto(order1());
    }

    @Test
    void findByIdNegativeTest() {
        doReturn(Optional.empty())
                .when(orderRepository).findById(1L);
        assertThrows(EntityNotFoundException.class, () -> orderService.findById(1L));
    }

//    @Test
//    void createOrderTest() {
//        doReturn(giftCertificateDto1())
//                .when(giftCertificateService).findById(orderDtoForCreateOrder().getGiftCertificateId());
//        doReturn(userDto3())
//                .when(userService).findById(orderDtoForCreateOrder().getUserId());
//        doReturn(order1WithoutId())
//                .when(orderMapper).toEntity(orderDtoForCreateOrderWithCost(), userDto3(), giftCertificateDto1());
//        doReturn(order1())
//                .when(orderRepository).save(order1WithoutId());
//        doReturn(orderDto1())
//                .when(orderMapper).toDto(order1());
//        OrderDto actual = orderService.createOrder(orderDtoForCreateOrder());
//        OrderDto expected = orderDto1();
//        assertEquals(expected, actual);
//        verify(giftCertificateService).findById(orderDtoForCreateOrder().getGiftCertificateId());
//        verify(userService).findById(orderDtoForCreateOrder().getUserId());
//        verify(orderMapper).toEntity(orderDtoForCreateOrderWithCost(), userDto3(), giftCertificateDto1());
//        verify(orderRepository).save(order1WithoutId());
//        verify(orderMapper).toDto(order1());
//    }

    @Test
    void createOrderTest() {
        doReturn(order1ForCreate())
                .when(orderMapper).toEntity(readOrderDtoForCreate());
        doReturn(giftCertificateDto1())
                .when(giftCertificateService).findById(readOrderDtoForCreate().getGiftCertificateId());
        doReturn(userDto3())
                .when(userService).findById(readOrderDtoForCreate().getUserId());
        doReturn(order1())
                .when(orderMapper).toFinalEntity(order1ForCreateWithCost(), userDto3(), giftCertificateDto1());
        doReturn(order1())
                .when(orderRepository).save(order1());
        doReturn(orderDto1())
                .when(orderMapper).toDto(order1());
        OrderDto actual = orderService.createOrder(readOrderDtoForCreate());
        OrderDto expected = orderDto1();
        assertEquals(expected, actual);
        verify(orderMapper).toEntity(readOrderDtoForCreate());
        verify(giftCertificateService).findById(readOrderDtoForCreate().getGiftCertificateId());
        verify(userService).findById(readOrderDtoForCreate().getUserId());
        verify(orderMapper).toFinalEntity(order1ForCreateWithCost(), userDto3(), giftCertificateDto1());
        verify(orderRepository).save(order1());
        verify(orderMapper).toDto(order1());
    }

    @Test
    void createOrderNegativeTest() {
        doThrow(EntityNotFoundException.class)
                .when(giftCertificateService).findById(readOrderDtoForCreate().getGiftCertificateId());
        assertThrows(EntityNotFoundException.class, () -> orderService.createOrder(readOrderDtoForCreate()));
    }

    @Test
    void createOrderNegativeTest2() {
        doReturn(giftCertificateDto1())
                .when(giftCertificateService).findById(readOrderDtoForCreate().getGiftCertificateId());
        doThrow(EntityNotFoundException.class)
                .when(userService).findById(readOrderDtoForCreate().getUserId());
        assertThrows(EntityNotFoundException.class, () -> orderService.createOrder(readOrderDtoForCreate()));
    }
}
