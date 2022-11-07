package ru.clevertec.ecl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.ReadGiftCertificateDto;
import ru.clevertec.ecl.entity.GiftCertificate;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.mapper.GiftCertificateMapper;
import ru.clevertec.ecl.repository.GiftCertificateRepository;
import ru.clevertec.ecl.service.impl.GiftCertificateServiceImpl;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.clevertec.ecl.dataForTest.GiftCertificateForTest.*;
import static ru.clevertec.ecl.dataForTest.GiftCertificateForTest.giftCertificate4;
import static ru.clevertec.ecl.dataForTest.TagForTest.tagForSave;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {

    @Mock
    private GiftCertificateRepository giftCertificateRepository;

    @Mock
    private GiftCertificateMapper giftCertificateMapper;

    @Mock
    private TagService tagService;

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    @Test
    void findAllTest() {
        doReturn(new PageImpl<>(singletonList(giftCertificate1())))
                .when(giftCertificateRepository).findAll(pageWithSizeOne());
        doReturn(singletonList(giftCertificateDto1()))
                .when(giftCertificateMapper).toDtoList(singletonList(giftCertificate1()));
        List<GiftCertificateDto> actual = giftCertificateService.findAll(pageWithSizeOne());
        List<GiftCertificateDto> expected = singletonList(giftCertificateDto1());
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findAll(pageWithSizeOne());
        verify(giftCertificateMapper).toDtoList(singletonList(giftCertificate1()));
    }

    @Test
    void findAllByPartOfNameTest() {
        doReturn(new PageImpl<>(singletonList(giftCertificate1())))
                .when(giftCertificateRepository).findAll(any(Example.class), any(PageRequest.class));
        doReturn(giftCertificateDto1())
                .when(giftCertificateMapper).toDto(giftCertificate1());
        List<GiftCertificateDto> actual = giftCertificateService
                .findAllByPartOfNameOrDescription("nes", null, pageable());
        List<GiftCertificateDto> expected = singletonList(giftCertificateDto1());
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findAll(any(Example.class), any(PageRequest.class));
        verify(giftCertificateMapper).toDto(giftCertificate1());
    }

    @Test
    void findAllByPartOfDescriptionTest() {
        doReturn(new PageImpl<>(singletonList(giftCertificate1())))
                .when(giftCertificateRepository).findAll(any(Example.class), any(PageRequest.class));
        doReturn(giftCertificateDto1())
                .when(giftCertificateMapper).toDto(giftCertificate1());
        List<GiftCertificateDto> actual = giftCertificateService
                .findAllByPartOfNameOrDescription(null, "fav", pageable());
        List<GiftCertificateDto> expected = singletonList(giftCertificateDto1());
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findAll(any(Example.class), any(PageRequest.class));
        verify(giftCertificateMapper).toDto(giftCertificate1());
    }

    @Test
    void findAllByTagNameTest() {
        doReturn(asList(giftCertificate1(), giftCertificate2(),
                giftCertificate3(), giftCertificate5()))
                .when(giftCertificateRepository).findAllByTagName("mood", pageWithSizeOne());
        doReturn(asList(giftCertificateDto1(), giftCertificateDto2(),
                giftCertificateDto3(), giftCertificateDto5()))
                .when(giftCertificateMapper).toDtoList(asList(giftCertificate1(), giftCertificate2(),
                        giftCertificate3(), giftCertificate5()));
        List<GiftCertificateDto> actual = giftCertificateService.findAllByTagName("mood", pageWithSizeOne());
        List<GiftCertificateDto> expected = asList(giftCertificateDto1(), giftCertificateDto2(),
                giftCertificateDto3(), giftCertificateDto5());
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findAllByTagName("mood", pageWithSizeOne());
        verify(giftCertificateMapper).toDtoList(asList(giftCertificate1(), giftCertificate2(),
                giftCertificate3(), giftCertificate5()));
    }

    @Test
    void findByIdTest() {
        doReturn(Optional.of(giftCertificate1()))
                .when(giftCertificateRepository).findById(1L);
        doReturn(giftCertificateDto1())
                .when(giftCertificateMapper).toDto(giftCertificate1());
        GiftCertificateDto actual = giftCertificateService.findById(1L);
        GiftCertificateDto expected = giftCertificateDto1();
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findById(1L);
        verify(giftCertificateMapper).toDto(giftCertificate1());
    }

    @Test
    void findByIdNegativeTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findById(1L);
        assertThrows(EntityNotFoundException.class, () -> giftCertificateService.findById(1L));
    }

    @Test
    void findAllBySeveralTagNamesTest() {
        List<String> tagNames = asList("summer", "fun", "nature");
        List<GiftCertificate> giftCertificates = asList(giftCertificate2(), giftCertificate3(),
                giftCertificate4(), giftCertificate5());
        doReturn(giftCertificates)
                .when(giftCertificateRepository).findAllBySeveralTagNames(tagNames, pageable());
        doReturn(asList(giftCertificateDto2(), giftCertificateDto3(),
                giftCertificateDto4(), giftCertificateDto5()))
                .when(giftCertificateMapper).toDtoList(giftCertificates);
                List<GiftCertificateDto> actual = giftCertificateService.findAllBySeveralTagNames(tagNames, pageable());
        List<GiftCertificateDto> expected = asList(giftCertificateDto2(), giftCertificateDto3(),
                giftCertificateDto4(), giftCertificateDto5());
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findAllBySeveralTagNames(tagNames, pageable());
        verify(giftCertificateMapper).toDtoList(giftCertificates);
    }

    @Test
    void saveTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        doReturn(singletonList(tagForSave()))
                .when(tagService).createTagList(any(ReadGiftCertificateDto.class));
        doReturn(giftCertificate1WithoutId())
                .when(giftCertificateMapper).toEntity(readGiftCertificateDto1(),
                        singletonList(tagForSave()));
        doReturn(giftCertificate1())
                .when(giftCertificateRepository).save(giftCertificate1WithoutId());
        doReturn(giftCertificateDto1())
                .when(giftCertificateMapper).toDto(giftCertificate1());
        GiftCertificateDto actual = giftCertificateService.save(readGiftCertificateDto1());
        GiftCertificateDto expected = giftCertificateDto1();
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        verify(tagService).createTagList(any(ReadGiftCertificateDto.class));
        verify(giftCertificateMapper).toEntity(readGiftCertificateDto1(),
                singletonList(tagForSave()));
        verify(giftCertificateRepository).save(giftCertificate1WithoutId());
        verify(giftCertificateMapper).toDto(giftCertificate1());
    }

    @Test
    void saveWithExistsNameTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        doReturn(singletonList(tagForSave()))
                .when(tagService).createTagList(any(ReadGiftCertificateDto.class));
        doReturn(giftCertificate1WithoutId())
                .when(giftCertificateMapper).toEntity(readGiftCertificateDto1(),
                        singletonList(tagForSave()));
        doThrow(EntityWithNameExistsException.class)
                .when(giftCertificateRepository).save(giftCertificate1WithoutId());
        assertThrows(EntityWithNameExistsException.class, () -> giftCertificateService.save(readGiftCertificateDto1()));
        verify(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        verify(tagService).createTagList(any(ReadGiftCertificateDto.class));
        verify(giftCertificateMapper).toEntity(readGiftCertificateDto1(),
                singletonList(tagForSave()));
        verify(giftCertificateRepository).save(giftCertificate1WithoutId());
    }

    @Test
    void updateNameTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        doReturn(Optional.of(giftCertificate1()))
                .when(giftCertificateRepository).findById(1L);
        doReturn(emptyList())
                .when(tagService).createTagList(any(ReadGiftCertificateDto.class));
        doReturn(giftCertificateAfterUpdateName())
                .when(giftCertificateMapper).toUpdateEntity(readGiftCertificateDtoUpdatedName(), giftCertificate1());
        doReturn(giftCertificateAfterUpdateName())
                .when(giftCertificateRepository).save(giftCertificateAfterUpdateName());
        doReturn(giftCertificateDtoUpdatedName())
                .when(giftCertificateMapper).toDto(giftCertificateAfterUpdateName());
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedName(), 1L);
        GiftCertificateDto expected = giftCertificateDtoUpdatedName();
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        verify(giftCertificateRepository).findById(1L);
        verify(tagService).createTagList(any(ReadGiftCertificateDto.class));
        verify(giftCertificateMapper).toUpdateEntity(readGiftCertificateDtoUpdatedName(), giftCertificate1());
        verify(giftCertificateRepository).save(giftCertificateAfterUpdateName());
        verify(giftCertificateMapper).toDto(giftCertificateAfterUpdateName());
    }

    @Test
    void updatePriceTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findByNameIgnoreCase(null);
        doReturn(Optional.of(giftCertificate1()))
                .when(giftCertificateRepository).findById(1L);
        doReturn(emptyList())
                .when(tagService).createTagList(any(ReadGiftCertificateDto.class));
        doReturn(giftCertificateAfterUpdatePrice())
                .when(giftCertificateMapper).toUpdateEntity(readGiftCertificateDtoUpdatedPrice(), giftCertificate1());
        doReturn(giftCertificateAfterUpdatePrice())
                .when(giftCertificateRepository).save(giftCertificateAfterUpdatePrice());
        doReturn(giftCertificateDtoUpdatedPrice())
                .when(giftCertificateMapper).toDto(giftCertificateAfterUpdatePrice());
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedPrice(), 1L);
        GiftCertificateDto expected = giftCertificateDtoUpdatedPrice();
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findByNameIgnoreCase(null);
        verify(giftCertificateRepository).findById(1L);
        verify(tagService).createTagList(any(ReadGiftCertificateDto.class));
        verify(giftCertificateMapper).toUpdateEntity(readGiftCertificateDtoUpdatedPrice(), giftCertificate1());
        verify(giftCertificateRepository).save(giftCertificateAfterUpdatePrice());
        verify(giftCertificateMapper).toDto(giftCertificateAfterUpdatePrice());
    }

    @Test
    void updateDescriptionTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findByNameIgnoreCase(null);
        doReturn(Optional.of(giftCertificate1()))
                .when(giftCertificateRepository).findById(1L);
        doReturn(emptyList())
                .when(tagService).createTagList(any(ReadGiftCertificateDto.class));
        doReturn(giftCertificateAfterUpdateDescription())
                .when(giftCertificateMapper)
                .toUpdateEntity(readGiftCertificateDtoUpdatedDescription(), giftCertificate1());
        doReturn(giftCertificateAfterUpdateDescription())
                .when(giftCertificateRepository).save(giftCertificateAfterUpdateDescription());
        doReturn(giftCertificateDtoUpdatedDescription())
                .when(giftCertificateMapper).toDto(giftCertificateAfterUpdateDescription());
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedDescription(), 1L);
        GiftCertificateDto expected = giftCertificateDtoUpdatedDescription();
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findByNameIgnoreCase(null);
        verify(giftCertificateRepository).findById(1L);
        verify(tagService).createTagList(any(ReadGiftCertificateDto.class));
        verify(giftCertificateMapper).toUpdateEntity(readGiftCertificateDtoUpdatedDescription(), giftCertificate1());
        verify(giftCertificateRepository).save(giftCertificateAfterUpdateDescription());
        verify(giftCertificateMapper).toDto(giftCertificateAfterUpdateDescription());
    }

    @Test
    void updateDurationTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findByNameIgnoreCase(null);
        doReturn(Optional.of(giftCertificate1()))
                .when(giftCertificateRepository).findById(1L);
        doReturn(emptyList())
                .when(tagService).createTagList(any(ReadGiftCertificateDto.class));
        doReturn(giftCertificateAfterUpdateDuration())
                .when(giftCertificateMapper)
                .toUpdateEntity(readGiftCertificateDtoUpdatedDuration(), giftCertificate1());
        doReturn(giftCertificateAfterUpdateDuration())
                .when(giftCertificateRepository).save(giftCertificateAfterUpdateDuration());
        doReturn(giftCertificateDtoUpdatedDuration())
                .when(giftCertificateMapper).toDto(giftCertificateAfterUpdateDuration());
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedDuration(), 1L);
        GiftCertificateDto expected = giftCertificateDtoUpdatedDuration();
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findByNameIgnoreCase(null);
        verify(giftCertificateRepository).findById(1L);
        verify(tagService).createTagList(any(ReadGiftCertificateDto.class));
        verify(giftCertificateMapper).toUpdateEntity(readGiftCertificateDtoUpdatedDuration(), giftCertificate1());
        verify(giftCertificateRepository).save(giftCertificateAfterUpdateDuration());
        verify(giftCertificateMapper).toDto(giftCertificateAfterUpdateDuration());
    }

    @Test
    void updateTagsTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findByNameIgnoreCase(null);
        doReturn(Optional.of(giftCertificate1()))
                .when(giftCertificateRepository).findById(1L);
        doReturn(emptyList())
                .when(tagService).createTagList(any(ReadGiftCertificateDto.class));
        doReturn(giftCertificateAfterUpdateTags())
                .when(giftCertificateMapper)
                .toUpdateEntity(readGiftCertificateDtoUpdatedTags(), giftCertificate1());
        doReturn(giftCertificateAfterUpdateTags())
                .when(giftCertificateRepository).save(giftCertificateAfterUpdateTags());
        doReturn(giftCertificateDtoUpdatedTags())
                .when(giftCertificateMapper).toDto(giftCertificateAfterUpdateTags());
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedTags(), 1L);
        GiftCertificateDto expected = giftCertificateDtoUpdatedTags();
        assertEquals(expected, actual);
        verify(giftCertificateRepository).findByNameIgnoreCase(null);
        verify(giftCertificateRepository).findById(1L);
        verify(tagService).createTagList(any(ReadGiftCertificateDto.class));
        verify(giftCertificateMapper).toUpdateEntity(readGiftCertificateDtoUpdatedTags(), giftCertificate1());
        verify(giftCertificateRepository).save(giftCertificateAfterUpdateTags());
        verify(giftCertificateMapper).toDto(giftCertificateAfterUpdateTags());
    }

    @Test
    void updateWithNotFoundIdTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findById(8L);
        assertThrows(EntityNotFoundException.class,
                () -> giftCertificateService.update(readGiftCertificateDtoUpdatedName(), 8L));
        verify(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        verify(giftCertificateRepository).findById(8L);
        verify(giftCertificateRepository, never()).save(giftCertificateAfterUpdateName());
    }

    @Test
    void updateWithExistsNameTest() {
        doThrow(EntityWithNameExistsException.class)
                .when(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        assertThrows(EntityWithNameExistsException.class,
                () -> giftCertificateService.update(readGiftCertificateDtoUpdatedName(), 1L));
        verify(giftCertificateRepository).findByNameIgnoreCase(any(String.class));
        verify(giftCertificateRepository, never()).save(giftCertificateAfterUpdateName());
    }

    @Test
    void deleteTest() {
        doReturn(Optional.of(giftCertificate1()))
                .when(giftCertificateRepository).findById(1L);
        assertDoesNotThrow(() -> giftCertificateService.delete(1L));
        verify(giftCertificateRepository).delete(giftCertificate1());
    }

    @Test
    void deleteWithNotFoundIdTest() {
        doReturn(Optional.empty())
                .when(giftCertificateRepository).findById(8L);
        assertThrows(EntityNotFoundException.class, () -> giftCertificateService.delete(8L));
        verify(giftCertificateRepository, never()).delete(giftCertificate1());
    }
}
