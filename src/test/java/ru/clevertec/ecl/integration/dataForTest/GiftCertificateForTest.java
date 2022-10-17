package ru.clevertec.ecl.integration.dataForTest;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.ReadGiftCertificateDto;
import ru.clevertec.ecl.entity.GiftCertificate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GiftCertificateForTest {

    public static GiftCertificate giftCertificate1() {
        return GiftCertificate.builder()
                .id(1L)
                .name("fitness")
                .description("description")
                .price(BigDecimal.valueOf(115.15))
                .duration(14)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tag1(), TagForTest.tag3()))
                .build();
    }

    public static ReadGiftCertificateDto readGiftCertificateDto() {
        return ReadGiftCertificateDto.builder()
                .name("fitness")
                .description("description")
                .price(BigDecimal.valueOf(115.15))
                .duration(14)
                .tags(Arrays.asList(TagForTest.readTagDto1(), TagForTest.readTagDto4()))
                .build();
    }

    public static GiftCertificate giftCertificate2() {
        return GiftCertificate.builder()
                .id(2L)
                .name("sport")
                .description("description")
                .price(BigDecimal.valueOf(75.75))
                .duration(10)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tag1(), TagForTest.tag2(), TagForTest.tag3()))
                .build();
    }

    public static GiftCertificate giftCertificate3() {
        return GiftCertificate.builder()
                .id(3L)
                .name("travel")
                .description("description")
                .price(BigDecimal.valueOf(102.05))
                .duration(7)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tag3(), TagForTest.tag5()))
                .build();
    }

    public static GiftCertificate giftCertificate4() {
        return GiftCertificate.builder()
                .id(4L)
                .name("dinner")
                .description("description")
                .price(BigDecimal.valueOf(55.55))
                .duration(10)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Collections.singletonList(TagForTest.tag4()))
                .build();
    }

    public static GiftCertificate giftCertificate5() {
        return GiftCertificate.builder()
                .id(5L)
                .name("painting")
                .description("description")
                .price(BigDecimal.valueOf(77.65))
                .duration(21)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tag3(), TagForTest.tag5()))
                .build();
    }

    public static GiftCertificate giftCertificateForSave() {
        return GiftCertificate.builder()
                .name("movie")
                .description("description")
                .price(BigDecimal.valueOf(118.5))
                .duration(14)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tag1(), TagForTest.tag4()))
                .build();
    }

    public static GiftCertificateDto giftCertificateDto1() {
        return GiftCertificateDto.builder()
                .id(1L)
                .name("fitness")
                .description("description")
                .price(BigDecimal.valueOf(115.15))
                .duration(14)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto1(), TagForTest.tagDto3()))
                .build();
    }

    public static GiftCertificateDto giftCertificateDto2() {
        return GiftCertificateDto.builder()
                .id(2L)
                .name("sport")
                .description("description")
                .price(BigDecimal.valueOf(75.75))
                .duration(10)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto1(), TagForTest.tagDto2(), TagForTest.tagDto3()))
                .build();
    }

    public static GiftCertificateDto giftCertificateDto3() {
        return GiftCertificateDto.builder()
                .id(3L)
                .name("travel")
                .description("description")
                .price(BigDecimal.valueOf(102.05))
                .duration(7)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto3(), TagForTest.tagDto5()))
                .build();
    }

    public static GiftCertificateDto giftCertificateDto4() {
        return GiftCertificateDto.builder()
                .id(4L)
                .name("dinner")
                .description("description")
                .price(BigDecimal.valueOf(55.55))
                .duration(10)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Collections.singletonList(TagForTest.tagDto4()))
                .build();
    }

    public static GiftCertificateDto giftCertificateDto5() {
        return GiftCertificateDto.builder()
                .id(5L)
                .name("painting")
                .description("description")
                .price(BigDecimal.valueOf(77.65))
                .duration(21)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto3(), TagForTest.tagDto5()))
                .build();
    }

    public static ReadGiftCertificateDto readGiftCertificateDtoForSave() {
        return ReadGiftCertificateDto.builder()
                .name("movie")
                .description("description")
                .price(BigDecimal.valueOf(118.50))
                .duration(14)
                .tags(Arrays.asList(TagForTest.readTagDto1(), TagForTest.readTagDto4()))
                .build();
    }

    public static GiftCertificateDto giftCertificateDtoForSave() {
        return GiftCertificateDto.builder()
                .id(6L)
                .name("movie")
                .description("description")
                .price(BigDecimal.valueOf(118.50))
                .duration(14)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto1(), TagForTest.tagDto4()))
                .build();
    }

    public static ReadGiftCertificateDto readGiftCertificateDtoUpdatedName() {
        return ReadGiftCertificateDto.builder()
                .name("build")
                .tags(Collections.emptyList())
                .build();
    }

    public static GiftCertificateDto giftCertificateDtoUpdatedName() {
        return GiftCertificateDto.builder()
                .id(1L)
                .name("build")
                .description("description")
                .price(BigDecimal.valueOf(115.15))
                .duration(14)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto1(), TagForTest.tagDto3()))
                .build();
    }

    public static ReadGiftCertificateDto readGiftCertificateDtoUpdatedPrice() {
        return ReadGiftCertificateDto.builder()
                .price(BigDecimal.valueOf(222.22))
                .tags(Collections.emptyList())
                .build();
    }

    public static GiftCertificateDto giftCertificateDtoUpdatedPrice() {
        return GiftCertificateDto.builder()
                .id(1L)
                .name("fitness")
                .description("description")
                .price(BigDecimal.valueOf(222.22))
                .duration(14)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto1(), TagForTest.tagDto3()))
                .build();
    }

    public static ReadGiftCertificateDto readGiftCertificateDtoUpdatedDescription() {
        return ReadGiftCertificateDto.builder()
                .description("new description")
                .tags(Collections.emptyList())
                .build();
    }

    public static GiftCertificateDto giftCertificateDtoUpdatedDescription() {
        return GiftCertificateDto.builder()
                .id(1L)
                .name("fitness")
                .description("new description")
                .price(BigDecimal.valueOf(115.15))
                .duration(14)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto1(), TagForTest.tagDto3()))
                .build();
    }

    public static ReadGiftCertificateDto readGiftCertificateDtoUpdatedDuration() {
        return ReadGiftCertificateDto.builder()
                .duration(10)
                .tags(Collections.emptyList())
                .build();
    }

    public static GiftCertificateDto giftCertificateDtoUpdatedDuration() {
        return GiftCertificateDto.builder()
                .id(1L)
                .name("fitness")
                .description("description")
                .price(BigDecimal.valueOf(115.15))
                .duration(10)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto1(), TagForTest.tagDto3()))
                .build();
    }

    public static ReadGiftCertificateDto readGiftCertificateDtoUpdatedTags() {
        return ReadGiftCertificateDto.builder()
                .tags(Collections.singletonList(TagForTest.readTagDto4()))
                .build();
    }

    public static GiftCertificateDto giftCertificateDtoUpdatedTags() {
        return GiftCertificateDto.builder()
                .id(1L)
                .name("fitness")
                .description("description")
                .price(BigDecimal.valueOf(115.15))
                .duration(14)
                .createDate(LocalDateTime.now().withNano(0))
                .lastUpdateDate(LocalDateTime.now().withNano(0))
                .tags(Arrays.asList(TagForTest.tagDto1(), TagForTest.tagDto3(), TagForTest.tagDto4()))
                .build();
    }

    public static ReadGiftCertificateDto readGiftCertificateDtoForUpdateWithExistsName() {
        return ReadGiftCertificateDto.builder()
                .name("sport")
                .tags(Collections.emptyList())
                .build();
    }

    public static List<GiftCertificate> giftCertificates() {
        return Arrays.asList(giftCertificate1(), giftCertificate2(), giftCertificate3(),
                giftCertificate4(), giftCertificate5());
    }

    public static List<GiftCertificateDto> giftCertificatesDto() {
        return Arrays.asList(giftCertificateDto1(), giftCertificateDto2(),
                giftCertificateDto3(), giftCertificateDto4(), giftCertificateDto5());
    }

    public static Pageable pageable() {
        return PageRequest.of(0, 20);
    }

    public static ExampleMatcher matcher() {
        return ExampleMatcher.matchingAny()
                .withMatcher("name",
                        ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("description",
                        ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
    }
}
