package ru.clevertec.ecl.mapper;

import org.mapstruct.*;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.ReadGiftCertificateDto;
import ru.clevertec.ecl.entity.GiftCertificate;
import ru.clevertec.ecl.entity.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(imports = LocalDateTime.class, uses = TagMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GiftCertificateMapper {

    @Mapping(target = "createDate", expression = "java(LocalDateTime.now().minusNanos(0))")
    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now().minusNanos(0))")
    @Mapping(source = "tags", target = "tags")
    GiftCertificate toEntity(ReadGiftCertificateDto readGiftCertificateDto, List<Tag> tags);

    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now().minusNanos(0))")
    @Mapping(target = "tags", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GiftCertificate toUpdateEntity(ReadGiftCertificateDto readGiftCertificateDto,
                                   @MappingTarget GiftCertificate giftCertificate);

    GiftCertificateDto toDto(GiftCertificate giftCertificate);

    List<GiftCertificateDto> toDtoList(List<GiftCertificate> giftCertificateList);
}
