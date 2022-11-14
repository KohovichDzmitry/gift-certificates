package ru.clevertec.ecl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.OrderDto;
import ru.clevertec.ecl.dto.ReadOrderDto;
import ru.clevertec.ecl.dto.UserDto;
import ru.clevertec.ecl.entity.Order;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.mapper.OrderMapper;
import ru.clevertec.ecl.repository.OrderRepository;
import ru.clevertec.ecl.service.GiftCertificateService;
import ru.clevertec.ecl.service.OrderService;
import ru.clevertec.ecl.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final GiftCertificateService giftCertificateService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> findAll(Pageable pageable) {
        return orderMapper.toDtoList(orderRepository
                .findAll((pageable)).toList());
    }

    @Override
    public OrderDto findById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Order.class, id)));
    }

    @Override
    public List<OrderDto> findAllByUserId(Long userId, Pageable pageable) {
        return orderMapper.toDtoList(orderRepository
                .findAllByUserId(userId, pageable));
    }

    @Override
    public Integer findLastSequenceValue() {
        return orderRepository.findLastSequenceValue();
    }

    @Override
    @Transactional
    public Integer setSequenceValue(Integer sequenceValue) {
        return orderRepository.setSequenceValue(sequenceValue);
    }

    @Override
    @Transactional
    public OrderDto createOrder(ReadOrderDto readOrderDto) {
        Order order = orderMapper.toEntity(readOrderDto);
        GiftCertificateDto giftCertificateDto = giftCertificateService.findById(readOrderDto.getGiftCertificateId());
        UserDto userDto = userService.findById(readOrderDto.getUserId());
        order.setCost(giftCertificateDto.getPrice());
        Order finalOrder = orderMapper.toFinalEntity(order, userDto, giftCertificateDto);
        return orderMapper.toDto(orderRepository.save(finalOrder));
    }
}
