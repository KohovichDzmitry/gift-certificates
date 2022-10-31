package ru.clevertec.ecl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadGiftCertificateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Positive
    private BigDecimal price;

    @Positive
    private Integer duration;
    private List<ReadTagDto> tags;
}
