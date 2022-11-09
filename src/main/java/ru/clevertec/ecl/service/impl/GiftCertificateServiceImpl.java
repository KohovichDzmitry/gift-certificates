package ru.clevertec.ecl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.ReadGiftCertificateDto;
import ru.clevertec.ecl.entity.GiftCertificate;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.mapper.GiftCertificateMapper;
import ru.clevertec.ecl.repository.GiftCertificateRepository;
import ru.clevertec.ecl.service.GiftCertificateService;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateRepository giftCertificateRepository;
    private final GiftCertificateMapper giftCertificateMapper;
    private final TagService tagService;

    @Override
    public List<GiftCertificateDto> findAll(Pageable pageable) {
        return giftCertificateMapper.toDtoList(giftCertificateRepository
                .findAll((pageable)).toList());
    }

    @Override
    public List<GiftCertificateDto> findAllByPartOfNameOrDescription(String name, String description,
                                                                     Pageable pageable) {
        return giftCertificateRepository.findAll(
                        Example.of(GiftCertificate.builder()
                                        .name(name)
                                        .description(description)
                                        .build(),
                                matcher()), pageable).stream()
                .map(giftCertificateMapper::toDto)
                .collect(toList());
    }

    @Override
    public GiftCertificateDto findById(Long id) {
        return giftCertificateMapper.toDto(findGiftCertificateById(id));
    }

    @Override
    public List<GiftCertificateDto> findAllByTagName(String tagName, Pageable pageable) {
        return giftCertificateMapper.toDtoList(giftCertificateRepository.findAllByTagName(tagName, pageable));
    }

    @Override
    public List<GiftCertificateDto> findAllBySeveralTagNames(List<String> tagNames, Pageable pageable) {
        return giftCertificateMapper.toDtoList(giftCertificateRepository.findAllBySeveralTagNames(tagNames, pageable));
    }

    @Override
    @Transactional
    public GiftCertificateDto save(ReadGiftCertificateDto readGiftCertificateDto) {
        existsByName(readGiftCertificateDto);
        return giftCertificateMapper.toDto(giftCertificateRepository
                .save(giftCertificateMapper.toEntity(readGiftCertificateDto,
                        tagService.createTagList(readGiftCertificateDto))));
    }

    @Override
    @Transactional
    public GiftCertificateDto update(ReadGiftCertificateDto readGiftCertificateDto, Long id) {
        existsByName(readGiftCertificateDto);
        GiftCertificate giftCertificateById = findGiftCertificateById(id);
        giftCertificateById.getTags().addAll(tagService.createTagList(readGiftCertificateDto));
        GiftCertificate giftCertificate = giftCertificateMapper
                .toUpdateEntity(readGiftCertificateDto, giftCertificateById);
        return giftCertificateMapper.toDto(giftCertificateRepository.save(giftCertificate));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        giftCertificateRepository.delete(findGiftCertificateById(id));
    }

    private GiftCertificate findGiftCertificateById(Long id) {
        return giftCertificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(GiftCertificate.class, id));
    }

    private void existsByName(ReadGiftCertificateDto readGiftCertificateDto) {
        giftCertificateRepository.findByNameIgnoreCase(readGiftCertificateDto.getName())
                .ifPresent(giftCertificate -> {
                    throw new EntityWithNameExistsException(GiftCertificate.class, readGiftCertificateDto.getName());
                });
    }

    private ExampleMatcher matcher() {
        return ExampleMatcher.matchingAny()
                .withMatcher("name",
                        ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("description",
                        ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
    }
}
