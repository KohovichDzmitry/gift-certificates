package ru.clevertec.ecl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.mapper.TagMapper;
import ru.clevertec.ecl.repository.TagRepository;
import ru.clevertec.ecl.service.impl.TagServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.clevertec.ecl.dataForTest.TagForTest.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TagMapper tagMapper;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    void findAllTest() {
        doReturn(new PageImpl<>(tags()))
                .when(tagRepository).findAll(pageable());
        doReturn(tagsDto())
                .when(tagMapper).toDtoList(tags());
        List<TagDto> actual = tagService.findAll(pageable());
        List<TagDto> expected = tagsDto();
        assertEquals(expected, actual);
        verify(tagRepository).findAll(pageable());
        verify(tagMapper).toDtoList(tags());
    }

    @Test
    void findByIdTest() {
        doReturn(Optional.of(tag1()))
                .when(tagRepository).findById(1L);
        doReturn(tagDto1())
                .when(tagMapper).toDto(tag1());
        TagDto actual = tagService.findById(1L);
        TagDto expected = tagDto1();
        assertEquals(expected, actual);
        verify(tagRepository).findById(1L);
        verify(tagMapper).toDto(tag1());
    }

    @Test
    void findByIdNegativeTest() {
        doReturn(Optional.empty())
                .when(tagRepository).findById(1L);
        assertThrows(EntityNotFoundException.class, () -> tagService.findById(1L));
    }

    @Test
    void findMostWidelyUsedTagTest() {
        doReturn(Optional.of(tag1()))
                .when(tagRepository).findMostWidelyUsedTag();
        doReturn(tagDto1())
                .when(tagMapper).toDto(tag1());
        TagDto actual = tagService.findMostWidelyUsedTag();
        TagDto expected = tagDto1();
        assertEquals(expected, actual);
        verify(tagRepository).findMostWidelyUsedTag();
        verify(tagMapper).toDto(tag1());
    }

    @Test
    void saveTest() {
        doReturn(Optional.empty())
                .when(tagRepository).findByNameIgnoreCase(any(String.class));
        doReturn(tag1WithoutId())
                .when(tagMapper).toEntity(readTagDto1());
        doReturn(tag1())
                .when(tagRepository).save(tag1WithoutId());
        doReturn(tagDto1())
                .when(tagMapper).toDto(tag1());
        TagDto actual = tagService.save(readTagDto1());
        TagDto expected = tagDto1();
        assertEquals(expected, actual);
        verify(tagRepository).findByNameIgnoreCase(any(String.class));
        verify(tagMapper).toEntity(readTagDto1());
        verify(tagRepository).save(tag1WithoutId());
        verify(tagMapper).toDto(tag1());
    }

    @Test
    void saveWithExistsNameTest() {
        doReturn(Optional.empty())
                .when(tagRepository).findByNameIgnoreCase(any(String.class));
        doReturn(tag1WithoutId())
                .when(tagMapper).toEntity(readTagDto1());
        doThrow(EntityWithNameExistsException.class)
                .when(tagRepository).save(tag1WithoutId());
        assertThrows(EntityWithNameExistsException.class, () -> tagService.save(readTagDto1()));
    }

    @Test
    void updateNameTest() {
        doReturn(Optional.empty())
                .when(tagRepository).findByNameIgnoreCase(any(String.class));
        doReturn(Optional.of(tag1()))
                .when(tagRepository).findById(1L);
        doReturn(tagAfterUpdate())
                .when(tagMapper).toUpdateEntity(readTagDtoForUpdate(), tag1());
        doReturn(tagAfterUpdate())
                .when(tagRepository).save(tagAfterUpdate());
        doReturn(tagDtoForUpdate())
                .when(tagMapper).toDto(tagAfterUpdate());
        TagDto actual = tagService.update(readTagDtoForUpdate(), 1L);
        TagDto expected = tagDtoForUpdate();
        assertEquals(expected, actual);
        verify(tagRepository).findByNameIgnoreCase(any(String.class));
        verify(tagRepository).findById(1L);
        verify(tagMapper).toUpdateEntity(readTagDtoForUpdate(), tag1());
        verify(tagRepository).save(tagAfterUpdate());
        verify(tagMapper).toDto(tagAfterUpdate());
    }

    @Test
    void updateWithNotFoundIdTest() {
        doReturn(Optional.empty())
                .when(tagRepository).findByNameIgnoreCase(any(String.class));
        doReturn(Optional.empty())
                .when(tagRepository).findById(8L);
        assertThrows(EntityNotFoundException.class, () -> tagService.update(readTagDtoForUpdate(), 8L));
        verify(tagRepository).findByNameIgnoreCase(any(String.class));
        verify(tagRepository).findById(8L);
        verify(tagRepository, never()).save(tag1());
    }

    @Test
    void deleteTest() {
        doReturn(Optional.of(tag1()))
                .when(tagRepository).findById(1L);
        assertDoesNotThrow(() -> tagService.delete(1L));
        verify(tagRepository).delete(tag1());
    }

    @Test
    void deleteWithNotFoundIdTest() {
        doReturn(Optional.empty())
                .when(tagRepository).findById(1L);
        assertThrows(EntityNotFoundException.class, () -> tagService.delete(1L));
        verify(tagRepository, never()).delete(tag1());
    }
}
