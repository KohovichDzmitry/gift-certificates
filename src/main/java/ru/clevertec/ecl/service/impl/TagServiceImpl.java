package ru.clevertec.ecl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.ReadGiftCertificateDto;
import ru.clevertec.ecl.dto.ReadTagDto;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.entity.Tag;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.mapper.TagMapper;
import ru.clevertec.ecl.repository.TagRepository;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagDto> findAll(Pageable pageable) {
        return tagMapper.toDtoList(tagRepository.findAll(pageable).toList());
    }

    @Override
    public TagDto findById(Long id) {
        return tagMapper.toDto(findTagById(id));
    }

    @Override
    public TagDto findMostWidelyUsedTag() {
        return tagMapper.toDto(tagRepository.findMostWidelyUsedTag()
                .orElseThrow(() -> new EntityNotFoundException("Tag not found")));
    }

    @Override
    public Integer findLastSequenceValue() {
        return tagRepository.findLastSequenceValue();
    }

    @Override
    @Transactional
    public TagDto save(ReadTagDto readTagDto) {
        existsByName(readTagDto);
        return tagMapper.toDto(tagRepository.save(tagMapper.toEntity(readTagDto)));
    }

    @Override
    @Transactional
    public TagDto update(ReadTagDto readTagDto, Long id) {
        existsByName(readTagDto);
        Tag tag = tagMapper.toUpdateEntity(readTagDto, findTagById(id));
        return tagMapper.toDto(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tagRepository.delete(findTagById(id));
    }

    @Override
    @Transactional
    public List<Tag> createTagList(ReadGiftCertificateDto readGiftCertificateDto) {
        return readGiftCertificateDto.getTags()
                .stream()
                .map(tag -> tagRepository.findByNameIgnoreCase(tag.getName())
                        .orElseGet(() -> tagRepository.save(Tag.builder().name(tag.getName()).build())))
                .collect(toList());
    }

    private Tag findTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Tag.class, id));
    }

    private void existsByName(ReadTagDto readTagDto) {
        tagRepository.findByNameIgnoreCase(readTagDto.getName())
                .ifPresent(giftCertificate -> {
                    throw new EntityWithNameExistsException(Tag.class, readTagDto.getName());
                });
    }
}
