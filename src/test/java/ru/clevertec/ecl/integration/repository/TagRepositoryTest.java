package ru.clevertec.ecl.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.ecl.entity.Tag;
import ru.clevertec.ecl.integration.IntegrationTestBase;
import ru.clevertec.ecl.repository.TagRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.clevertec.ecl.integration.dataForTest.TagForTest.*;

@RequiredArgsConstructor
public class TagRepositoryTest extends IntegrationTestBase {

    private final TagRepository tagRepository;

    @Test
    void FindAllTest() {
        List<Tag> actual = tagRepository.findAll(PageRequest.of(0, 20)).getContent();
        assertEquals(tags(), actual);
    }

    @Test
    void findByIdTest() {
        Optional<Tag> optional = tagRepository.findById(1L);
        optional.ifPresent(tag -> assertEquals(tag1(), tag));
    }

    @Test
    void findByName() {
        Optional<Tag> optional = tagRepository.findByNameIgnoreCase("summer");
        optional.ifPresent(tag -> assertEquals(tag4(), tag));
    }

    @Test
    void saveTest() {
        Tag actual = tagRepository.save(tagForSave());
        tagRepository.flush();
        assertEquals(tagForSave(), actual);
    }

    @Test
    void deleteTest() {
        tagRepository.deleteById(1L);
        Optional<Tag> optional = tagRepository.findById(1L);
        assertFalse(optional.isPresent());
    }
}
