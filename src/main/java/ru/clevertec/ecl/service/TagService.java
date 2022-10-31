package ru.clevertec.ecl.service;

import ru.clevertec.ecl.dto.ReadGiftCertificateDto;
import ru.clevertec.ecl.dto.ReadTagDto;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.entity.Tag;

import java.util.List;

public interface TagService extends GenericService<ReadTagDto, TagDto> {

    TagDto findMostWidelyUsedTag();

    List<Tag> createTagList(ReadGiftCertificateDto readGiftCertificateDto);
}
