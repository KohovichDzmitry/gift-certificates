package ru.clevertec.ecl.service;

import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.dto.OrderDto;
import ru.clevertec.ecl.dto.ReadOrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAll(Pageable pageable);

    OrderDto findById(Long id);

    List<OrderDto> findAllByUserId(Long userId, Pageable pageable);

    OrderDto createOrder(ReadOrderDto readOrderDto);
}
