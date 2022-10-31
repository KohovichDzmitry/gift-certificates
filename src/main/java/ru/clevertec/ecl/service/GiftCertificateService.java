package ru.clevertec.ecl.service;

import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.ReadGiftCertificateDto;

import java.util.List;

public interface GiftCertificateService extends GenericService<ReadGiftCertificateDto, GiftCertificateDto> {

    List<GiftCertificateDto> findAllByTagName(String tagName, Pageable pageable);

    List<GiftCertificateDto> findAllByPartOfNameOrDescription(String name, String description, Pageable pageable);

    List<GiftCertificateDto> findAllBySeveralTagNames(List<String> tagNames, Pageable pageable);
}
