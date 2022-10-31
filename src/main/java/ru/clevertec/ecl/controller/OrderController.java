package ru.clevertec.ecl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.dto.OrderDto;
import ru.clevertec.ecl.dto.ReadOrderDto;
import ru.clevertec.ecl.service.OrderService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(orderService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@Positive @PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<OrderDto>> findAllByUserId(@Positive @PathVariable Long userId,
                                                          Pageable pageable) {
        return ResponseEntity.ok(orderService.findAllByUserId(userId, pageable));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrderByGiftCertificateId(@Valid @RequestBody ReadOrderDto readOrderDto) {
        return new ResponseEntity<>(orderService.createOrder(readOrderDto), HttpStatus.CREATED);
    }
}
