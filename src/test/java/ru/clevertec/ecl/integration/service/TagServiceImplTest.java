package ru.clevertec.ecl.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.integration.IntegrationTestBase;
import ru.clevertec.ecl.repository.TagRepository;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.clevertec.ecl.dataForTest.TagForTest.*;

@RequiredArgsConstructor
public class TagServiceImplTest extends IntegrationTestBase {

    private final TagService tagService;
    private final TagRepository tagRepository;

    @Test
    void findAllTest() {
        List<TagDto> actual = tagService.findAll(pageable());
        assertEquals(tagsDto(), actual);
    }

    @Test
    void findByIdTest() {
        TagDto actual = tagService.findById(1L);
        assertEquals(tagDto1(), actual);
    }

    @Test
    void findByIdNegativeTest() {
        assertThrows(EntityNotFoundException.class, () -> tagService.findById(8L));
    }

    @Test
    void findMostWidelyUsedTagTest() {
        TagDto actual = tagService.findMostWidelyUsedTag();
        assertEquals(tagDto3(), actual);
    }

    @Test
    void saveTest() {
        TagDto actual = tagService.save(readTagDtoForSave());
        tagRepository.flush();
        assertEquals(tagDtoForSave(), actual);
    }

    @Test
    void saveWithExistsNameTest() {
        assertThrows(EntityWithNameExistsException.class, () -> tagService.save(readTagDto1()));
    }

    @Test
    void updateNameTest() {
        TagDto actual = tagService.update(readTagDtoForUpdate(), 1L);
        tagRepository.flush();
        assertEquals(tagDtoForUpdate(), actual);
    }

    @Test
    void updateWithNotFoundIdTest() {
        assertThrows(EntityNotFoundException.class, () -> tagService.update(readTagDtoForUpdate(), 8L));
    }

    @Test
    void deleteTest() {
        tagService.delete(1L);
        tagRepository.flush();
        assertThrows(EntityNotFoundException.class, () -> tagService.findById(1L));
    }

    @Test
    void deleteWithNotFoundIdTest() {
        assertThrows(EntityNotFoundException.class, () -> tagService.delete(8L));
    }
}
