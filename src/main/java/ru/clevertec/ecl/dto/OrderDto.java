package ru.clevertec.ecl.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class OrderDto {

    Long id;
    BigDecimal cost;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime purchaseTimestamp;

    Long userId;
    Long giftCertificateId;
}
