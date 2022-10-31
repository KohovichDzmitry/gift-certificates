package ru.clevertec.ecl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadOrderDto {

    Long id;

    @Positive
    private BigDecimal cost;

    @Positive
    private Long userId;

    @Positive
    private Long giftCertificateId;
}
