package ru.clevertec.ecl.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class GiftCertificateDto {

    Long id;
    String name;
    String description;
    BigDecimal price;
    Integer duration;
    List<TagDto> tags;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime createDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime lastUpdateDate;
}
